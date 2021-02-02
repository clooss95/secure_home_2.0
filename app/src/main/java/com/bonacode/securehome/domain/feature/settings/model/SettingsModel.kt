package com.bonacode.securehome.domain.feature.settings.model

data class SettingsModel(
    val phoneNumber: String,
    val pinCode: String,
    val applicationPinCode: String,
    val applicationViaPinProtectionEnabled: Boolean
)
