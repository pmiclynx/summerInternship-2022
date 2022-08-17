package com.summer.internship.tvtracker.data

import android.util.Log
import com.summer.internship.tvtracker.domain.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRemoteDataSource : MoviesDataSource {
    private val themoviedb: Themoviedb = createMovieAPI()

    override fun getPopular(movieResponseListener: MovieResponseListener) {
        generateMovieList(themoviedb.getPopular(), movieResponseListener)
    }

    override fun getTopRated(movieResponseListener: MovieResponseListener) {
        generateMovieList(themoviedb.getTopRated(), movieResponseListener)
    }

    override fun getMovieDetails(id: Long, detailsResponseListener: DetailsResponseListener) {
        getMovieDetails(themoviedb.getMovieDetails(id), detailsResponseListener)
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
                movieResponseListener.onError()
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
                it.posterPath = "https://image.tmdb.org/t/p/w342${posterUrl}"
                detailsResponseListener.onDetailsReceived(it)
            },
            onError = {
                detailsResponseListener.onError()
            })
    }

    private fun <T> executeAPICall(
        apiCall: Call<T>,
        onSuccess: (T) -> Unit,
        onError: () -> Unit
    ) {

        apiCall.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let {
                    onSuccess(it)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.d("myTag", "Error loading movies")
                onError()
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