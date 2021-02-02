package com.bonacode.securehome.ui.feature.setup.enterpincode

import androidx.core.text.isDigitsOnly

fun String.isAlarmPinCodeValid(): Boolean = this.isDigitsOnly() && this.length >= 2
fun String.isApplicationPinCodeValid(): Boolean = this.isDigitsOnly() && this.length == 4
