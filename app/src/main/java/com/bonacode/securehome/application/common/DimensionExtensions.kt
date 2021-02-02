package com.bonacode.securehome.application.common

import android.content.res.Resources

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()