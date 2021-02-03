package com.bonacode.securehome.domain.feature.action.usecase.system

import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.ActionProcessor
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.model.SmsSendResult
import com.bonacode.securehome.domain.feature.action.usecase.ActionUseCase
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import javax.inject.Inject

class CheckSimCredit @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider,
    private val historyDataSource: ActionHistoryDataSource,
    private val actionProcessor: ActionProcessor
) : ActionUseCase<Unit> {

    override val actionType: ActionType = ActionType.CHECK_SIM_CREDIT

    override suspend fun invoke(params: Unit): Pair<SmsSendResult, ActionModel> {
        val message = actionCommandBuilderProvider.provideActionCommandBuilder()
            .checkSimCredit()

        val result = actionProcessor.processAction(message)
        historyDataSource.saveActionHistory(actionType, message)

        return result to BaseActionModel(actionType, message)
    }
}
