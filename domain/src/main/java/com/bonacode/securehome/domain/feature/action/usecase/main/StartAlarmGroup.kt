package com.bonacode.securehome.domain.feature.action.usecase.main

import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.ActionProcessor
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.model.SmsSendResult
import com.bonacode.securehome.domain.feature.action.usecase.ActionUseCase
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import javax.inject.Inject

class StartAlarmGroup @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider,
    private val historyDataSource: ActionHistoryDataSource,
    private val actionProcessor: ActionProcessor
) : ActionUseCase<StartAlarmGroup.Params> {

    override val actionType: ActionType = ActionType.START_ALARM_GROUP

    override suspend fun invoke(params: Params): Pair<SmsSendResult, ActionModel> {
        val message = actionCommandBuilderProvider.provideActionCommandBuilder()
            .startAlarmGroup(group = params.group)

        val result = actionProcessor.processAction(message)
        historyDataSource.saveActionHistory(actionType, message)

        return result to BaseActionModel(actionType, message)
    }

    data class Params(
        val group: String
    )
}
