package com.bonacode.securehome.data.feature.action

import com.bonacode.securehome.domain.feature.action.ActionCommandBuilder
import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.AlarmSystemLanguage
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import javax.inject.Inject

object ActionCommandBuilderFactory {
    fun create(alarmSystemLanguage: AlarmSystemLanguage): ActionCommandBuilder =
        when (alarmSystemLanguage) {
            AlarmSystemLanguage.POLISH -> ActionCommandBuilderPolishSystem
        }
}

class ActionCommandBuilderProviderImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource
) : ActionCommandBuilderProvider {

    override fun provideActionCommandBuilder(): ActionCommandBuilder = ActionCommandBuilderFactory.create(
        settingsDataSource.getAlarmSystemLanguage()
    )
}
