package com.summer.internship.tvtracker.di

import com.summer.internship.tvtracker.data.Themoviedb
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {

    single {
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
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        retrofit.create(Themoviedb::class.java)
    }

}
