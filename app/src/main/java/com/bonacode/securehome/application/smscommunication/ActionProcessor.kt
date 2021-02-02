package com.bonacode.securehome.application.smscommunication

import com.bonacode.securehome.application.config.PIN_CODE_PATTERN
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import javax.inject.Inject

interface ActionProcessor {
    suspend fun processAction(message: String): SmsSendResult
}

class ActionProcessorImpl @Inject constructor(
    private val smsSender: SmsSender,
    private val settingsDataSource: SettingsDataSource
) : ActionProcessor {
    override suspend fun processAction(message: String): SmsSendResult {
        val settings = settingsDataSource.getSettings()

        return smsSender.sendSms(
            message = message.replace(PIN_CODE_PATTERN, settings.pinCode),
            phoneNumber = settings.phoneNumber
        )
    }
}
