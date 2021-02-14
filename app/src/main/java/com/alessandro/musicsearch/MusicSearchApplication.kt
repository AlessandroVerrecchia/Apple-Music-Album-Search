package com.alessandro.musicsearch

import android.app.Application
import com.alessandro.musicsearch.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MusicSearchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MusicSearchApplication)
            modules(
                listOf(appModule)
            )
        }
    }

}