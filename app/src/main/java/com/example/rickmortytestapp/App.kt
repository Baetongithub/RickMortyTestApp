package com.example.rickmortytestapp

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import com.example.rickmortytestapp.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    @ExperimentalPagingApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = this@App)
            modules(koinModules)
        }
    }
}