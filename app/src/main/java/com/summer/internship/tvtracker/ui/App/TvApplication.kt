package com.summer.internship.tvtracker.ui.App

import android.app.Application
import com.summer.internship.tvtracker.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TvApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@TvApplication)
            modules(dataModule, roomModule, retrofitModule, dataSourceModule, firebaseModule)
        }
    }
}