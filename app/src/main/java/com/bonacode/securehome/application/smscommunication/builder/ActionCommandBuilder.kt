package com.bonacode.securehome.application.smscommunication.builder

import com.bonacode.securehome.application.smscommunication.AlarmSystemLanguage
import com.bonacode.securehome.application.smscommunication.builder.impl.ActionCommandBuilderPolishSystem
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import javax.inject.Inject

interface ActionCommandBuilderProvider {
    fun provideActionCommandBuilder(): ActionCommandBuilder
}

class ActionCommandBuilderProviderImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource
) : ActionCommandBuilderProvider {

    override fun provideActionCommandBuilder(): ActionCommandBuilder = ActionCommandBuilder.create(
        settingsDataSource.getAlarmSystemLanguage()
    )
}

interface ActionCommandBuilder {
    fun startAlarm(): String
    fun startAlarmHome(): String
    fun startAlarmGroup(group: String): String
    fun stopAlarm(): String
    fun startAlarmPartition(partition: Int): String
    fun startAlarmPartitionHome(partition: Int): String
    fun startAlarmGroupInPartition(group: String, partition: Int): String
    fun stopAlarmPartition(partition: Int): String
    fun blockLine(line: Int): String
    fun unblockLine(line: Int): String
    fun activateEntry(entry: Int): String
    fun inactivateEntry(entry: Int): String
    fun checkSystemStatus(): String
    fun checkLastAlarm(): String
    fun checkSimCredit(): String

    companion object Factory {
        fun create(alarmSystemLanguage: AlarmSystemLanguage): ActionCommandBuilder =
            when (alarmSystemLanguage) {
                AlarmSystemLanguage.POLISH -> ActionCommandBuilderPolishSystem
            }
    }
}
