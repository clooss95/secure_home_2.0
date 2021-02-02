package com.bonacode.securehome.domain.feature.action.usecase

import com.bonacode.securehome.application.config.PIN_CODE_PATTERN
import com.bonacode.securehome.application.smscommunication.SmsSendResult
import com.bonacode.securehome.application.smscommunication.SmsSender
import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource
import javax.inject.Inject

class SendDefaultAction @Inject constructor(
    private val smsSender: SmsSender,
    private val settingsDataSource: SettingsDataSource,
    private val historyDataSource: ActionHistoryDataSource
) : UseCase<SendDefaultAction.Params, SmsSendResult> {

    override suspend fun invoke(params: Params): SmsSendResult {
        val settings = settingsDataSource.getSettings()

        val result = smsSender.sendSms(
            message = params.model.smsCommand.replace(PIN_CODE_PATTERN, settings.pinCode),
            phoneNumber = settings.phoneNumber
        )

        historyDataSource.saveActionHistory(
            actionType = params.model.actionType,
            smsCommand = params.model.smsCommand
        )

        return result
    }

    data class Params(
        val model: ActionModel
    )
}
