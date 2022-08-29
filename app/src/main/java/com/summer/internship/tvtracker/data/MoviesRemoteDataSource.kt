package com.summer.internship.tvtracker.data

import android.util.Log
import androidx.room.Room
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.summer.internship.tvtracker.data.room.Favorite
import com.summer.internship.tvtracker.data.room.database.AppDatabase
import com.summer.internship.tvtracker.domain.*
import com.summer.internship.tvtracker.domain.details.OnAddListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

object MoviesRemoteDataSource : MoviesDataSource {
    private val themoviedb: Themoviedb = createMovieAPI()
    private val db = Firebase.firestore
    private val favorites = db.collection("favorites")
    private val userID = Firebase.auth.currentUser?.uid
//    private val dataBase = Room.databaseBuilder(applicationContext,
//        AppDatabase::class.java, "Favorite"
//    ).build()

    override fun getPopular(movieResponseListener: MovieResponseListener) {
        generateMovieList(themoviedb.getPopular(), movieResponseListener)
    }

    override fun getTopRated(movieResponseListener: MovieResponseListener) {
        generateMovieList(themoviedb.getTopRated(), movieResponseListener)
    }

    override fun getMovieDetails(id: Long, detailsResponseListener: DetailsResponseListener) {
        getMovieDetails(themoviedb.getMovieDetails(id), detailsResponseListener)
    }

    override fun addFavorite(
        detailsResponse: TvDetailsResponse,
        id: Long?,
        onAddListener: OnAddListener
    ) {
        userID?.let {
            val favoritesRef = favorites.document(it).collection("favoriteMovies")
//            favoritesRef.add(detailsResponse)
            id?.let {
                favoritesRef
                    .document(id.toString())
                    .get()
                    .addOnSuccessListener { result ->
                        if (!result.exists()) {
//                            val favoriteDao = database.favoriteDao()
//                            val fav: Favorite = Favorite(
//                                id,
//                                detailsResponse.backdropPath,
//                                detailsResponse.overView,
//                                detailsResponse.posterPath,
//                                detailsResponse.voteAverage,
//                                detailsResponse.name
//                            )
//                            favoriteDao.insert(fav)
                            favoritesRef.document(id.toString()).set(detailsResponse)
                            onAddListener.onAdd()

                        } else {
                            onAddListener.onFail()
                        }
                    }
                    .addOnFailureListener { exp ->
                        Log.d("aaaaaaa", exp.toString())
                        onAddListener.onFail()
                    }
            }
        }
    }

    private fun generateMovieList(
        apicall: Call<QuoteList>,
        movieResponseListener: MovieResponseListener
    ) {
        executeAPICall(
            apiCall = apicall,
            onSuccess = {
                it.results.map { result ->
                    Movie(
                        result.name,
                        "https://image.tmdb.org/t/p/w500" + result.backdrop_path,
                        result.id
                    )
                }.let { list ->
                    movieResponseListener.onMoviesReceived(list)
                }
            },
            onError = {
                movieResponseListener.onError(it)
            })
    }

    private fun getMovieDetails(
        apicall: Call<TvDetailsResponse>,
        detailsResponseListener: DetailsResponseListener
    ) {
        executeAPICall(
            apiCall = apicall,
            onSuccess = {
                val url = it.backdropPath
                val posterUrl = it.posterPath
                it.backdropPath = "https://image.tmdb.org/t/p/w780${url}"
                it.posterPath = "https://image.tmdb.org/t/p/w500${posterUrl}"
                detailsResponseListener.onDetailsReceived(it)
            },
            onError = {
                detailsResponseListener.onError()
            })
    }

    private fun <T> executeAPICall(
        apiCall: Call<T>,
        onSuccess: (T) -> Unit,
        onError: (e:Throwable) -> Unit
    ) {

        apiCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let {
                    onSuccess(it)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                onError(t)
            }
        })

    }

    private fun createMovieAPI(): Themoviedb {

        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                var original = chain.request()
                val token = "563e3b53b3c3a7da6ceae87959d74162"
                val url = original.url.newBuilder().addQueryParameter("api_key", token).build()
                original = original.newBuilder().url(url).build()
                return@addInterceptor chain.proceed(original)
            }
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(Themoviedb::class.java)
    }

}