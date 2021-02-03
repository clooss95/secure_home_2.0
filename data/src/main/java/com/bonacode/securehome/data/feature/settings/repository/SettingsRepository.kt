package com.bonacode.securehome.data.feature.settings.repository

import android.content.SharedPreferences
import com.bonacode.securehome.data.common.boolean
import com.bonacode.securehome.data.common.string
import com.bonacode.securehome.domain.feature.action.model.AlarmSystemLanguage
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import com.bonacode.securehome.domain.feature.settings.model.SettingsModel
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    sharedPreferences: SharedPreferences
) : SettingsDataSource {

    private var _phoneNumber by sharedPreferences.string("", KEY_PHONE_NUMBER)
    private var _pinCode by sharedPreferences.string("", KEY_PIN_CODE)
    private var _applicationPinCode by sharedPreferences.string("", KEY_APPLICATION_PIN_CODE)
    private var _configurationDone by sharedPreferences.boolean(false, KEY_CONFIGURATION_DONE)
    private var _applicationViaPinProtectionEnabled by sharedPreferences.boolean(
        false,
        KEY_PIN_PROTECTION_ENABLED
    )

    override fun onConfigurationDone() {
        _configurationDone = true
    }

    override fun isConfigurationDone(): Boolean = _configurationDone

    override fun getAlarmSystemLanguage(): AlarmSystemLanguage = AlarmSystemLanguage.POLISH

    override suspend fun updateSettings(settingsModel: SettingsModel) {
        _phoneNumber = settingsModel.phoneNumber
        _pinCode = settingsModel.pinCode
        _applicationPinCode = settingsModel.applicationPinCode
        _applicationViaPinProtectionEnabled = settingsModel.applicationViaPinProtectionEnabled
    }

    override suspend fun getSettings(): SettingsModel = SettingsModel(
        phoneNumber = _phoneNumber ?: "",
        pinCode = _pinCode ?: "",
        applicationPinCode = _applicationPinCode ?: "",
        applicationViaPinProtectionEnabled = _applicationViaPinProtectionEnabled
    )

    private companion object {
        private const val KEY_PHONE_NUMBER = "shared_prefs_key_phone_number"
        private const val KEY_PIN_CODE = "shared_prefs_key_pin_code"
        private const val KEY_APPLICATION_PIN_CODE = "shared_prefs_key_application_pin_code"
        private const val KEY_CONFIGURATION_DONE = "shared_prefs_key_configuration_done"
        private const val KEY_PIN_PROTECTION_ENABLED = "shared_prefs_key_pin_protection_enabled"
    }
}
