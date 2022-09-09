package com.summer.internship.tvtracker.data

import android.util.Log
import androidx.room.Room
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.summer.internship.tvtracker.data.room.Favorite
import com.summer.internship.tvtracker.data.room.database.AppDatabase
import com.summer.internship.tvtracker.domain.*
import com.summer.internship.tvtracker.domain.details.OnAddListener
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object MoviesRemoteDataSource : MoviesDataSource {
    private val themoviedb: Themoviedb = createMovieAPI()
    private val db = Firebase.firestore
    private val favorites = db.collection("favorites")
    private val userID = Firebase.auth.currentUser?.uid

    override fun getPopular(): Single<QuoteList> {
        return themoviedb.getPopular()
    }

    override fun getTopRated(): Single<QuoteList> {
        return themoviedb.getTopRated()
    }

    override fun getMovieDetails(id: Long): Single<TvDetailsResponse> {
        return themoviedb.getMovieDetails(id)
    }

    override fun addFavorite(
        detailsResponse: TvDetailsResponse,
        id: Long?
    ): Single<String> {
        return Single.create { emitter ->
            userID?.let {
                val favoritesRef = favorites.document(it).collection("favoriteMovies")
                id?.let {
                    favoritesRef
                        .document(id.toString())
                        .get()
                        .addOnSuccessListener { result ->
                            if (!result.exists()) {
                                favoritesRef.document(id.toString()).set(
                                    MovieFavorite(
                                        detailsResponse.backdropPath,
                                        detailsResponse.overView,
                                        detailsResponse.posterPath,
                                        detailsResponse.voteAverage,
                                        detailsResponse.name,
                                        DateTimeFormatter
                                            .ofPattern("yyyy-MM-dd")
                                            .withZone(ZoneOffset.UTC)
                                            .format(Instant.now())
                                    )
                                )
                                emitter.onSuccess(id.toString())

                            } else {
                                emitter.onError(Throwable("movie already exists"))
                            }
                        }
                        .addOnFailureListener { exp ->
                            Log.d("aaaaaaa", exp.toString())
                            emitter.onError(exp)
                        }
                }
            }
        }

    }

    override fun getFavorites(): Single<List<FavoriteMovie>> {
        return Single.create { emitter ->
            userID?.let {
                val favoritesRef = favorites.document(it).collection("favoriteMovies")
                favoritesRef
                    .get()
                    .addOnSuccessListener { result ->
                        val favoriteMovieList: ArrayList<FavoriteMovie> =
                            arrayListOf<FavoriteMovie>()
                        for (document in result) {
                            Log.d("ao", document.data.toString())
                            val response = document.toObject<FavoriteResponse>()
                            favoriteMovieList.add(
                                FavoriteMovie(
                                    response.name!!,
                                    response.backdropPath!!,
                                    response.date!!,
                                    document.id
                                )
                            )
                        }
                        Log.d("aa", favoriteMovieList.toString())
                        emitter.onSuccess(favoriteMovieList)
                    }
                    .addOnFailureListener { exp ->
                        Log.d("aaaaaaa", exp.toString())
                        emitter.onError(exp)
                    }
            }
        }
    }

    override fun deleteFavorite(id: String) {
        userID?.let {
            val favoritesRef = favorites.document(it).collection("favoriteMovies")
            favoritesRef.document(id).delete()
                .addOnSuccessListener {
                    Log.d("airb", id)
                }
                .addOnFailureListener {
                        e -> Log.d("airb", "Error deleting document", e)
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
        onError: (e: Throwable) -> Unit
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        return retrofit.create(Themoviedb::class.java)
    }

}