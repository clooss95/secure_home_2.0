package com.bonacode.securehome.data.feature.action

import com.bonacode.securehome.application.config.PIN_CODE_PATTERN
import com.bonacode.securehome.domain.feature.action.ActionProcessor
import com.bonacode.securehome.domain.feature.action.SmsSender
import com.bonacode.securehome.domain.feature.action.model.SmsSendResult
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import javax.inject.Inject

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
