package com.bonacode.securehome.application

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.karumi.dexter.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SecureHomeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            // todo -  init crash reporting tree here
        }
    }
}