package com.bonacode.securehome.ui.feature.setup.enterphonenumber

fun CharSequence.isValidPhoneNumber(): Boolean =
    android.util.Patterns.PHONE.matcher(this).matches()
