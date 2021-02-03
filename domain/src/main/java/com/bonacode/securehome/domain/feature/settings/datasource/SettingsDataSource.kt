package com.bonacode.securehome.domain.feature.settings.datasource

import com.bonacode.securehome.domain.feature.action.model.AlarmSystemLanguage
import com.bonacode.securehome.domain.feature.settings.model.SettingsModel

interface SettingsDataSource {
    suspend fun updateSettings(settingsModel: SettingsModel)
    suspend fun getSettings(): SettingsModel

    fun onConfigurationDone()
    fun isConfigurationDone(): Boolean
    fun getAlarmSystemLanguage(): AlarmSystemLanguage
}
