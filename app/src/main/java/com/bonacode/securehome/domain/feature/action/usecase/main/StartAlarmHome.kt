package com.bonacode.securehome.domain.feature.action.usecase.main

import com.bonacode.securehome.application.smscommunication.ActionProcessor
import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.application.smscommunication.SmsSendResult
import com.bonacode.securehome.application.smscommunication.builder.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.ActionUseCase
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import javax.inject.Inject

class StartAlarmHome @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider,
    private val historyDataSource: ActionHistoryDataSource,
    private val actionProcessor: ActionProcessor
) : ActionUseCase<Unit> {

    override val actionType: ActionType = ActionType.START_ALARM_HOME

    override suspend fun invoke(params: Unit): Pair<SmsSendResult, ActionModel> {
        val message = actionCommandBuilderProvider.provideActionCommandBuilder()
            .startAlarmHome()

        val result = actionProcessor.processAction(message)
        historyDataSource.saveActionHistory(actionType, message)

        return result to BaseActionModel(actionType, message)
    }
}
