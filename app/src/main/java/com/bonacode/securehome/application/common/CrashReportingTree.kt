package com.bonacode.securehome.application.common

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

object CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        FirebaseCrashlytics.getInstance().log(message)
        if (t != null) {
            FirebaseCrashlytics.getInstance().recordException(t)
        }
    }
}