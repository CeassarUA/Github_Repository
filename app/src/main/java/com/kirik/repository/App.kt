package com.kirik.repository

import android.app.Application
import com.kirik.repository.di.appModule
import com.kirik.repository.di.networkModule
import com.kirik.repository.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                appModule,
                viewModelModule,
                networkModule
            ))
        }
    }
}