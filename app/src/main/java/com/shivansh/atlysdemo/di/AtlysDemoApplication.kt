package com.shivansh.atlysdemo.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AtlysDemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AtlysDemoApplication)
            modules(appModule)
        }
    }
}