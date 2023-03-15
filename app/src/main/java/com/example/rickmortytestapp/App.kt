package com.example.rickmortytestapp

import android.app.Application
import com.example.rickmortytestapp.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(androidContext = this@App)
            modules(koinModules)
        }
    }
}