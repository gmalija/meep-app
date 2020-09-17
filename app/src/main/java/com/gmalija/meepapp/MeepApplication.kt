package com.gmalija.meepapp

import android.app.Application
import com.gmalija.meepapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MeepApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@MeepApplication)
            modules(listOf(retrofitModule, clusterModule, repositoryModule, useCaseModule, viewModelModule))
        }
    }
}