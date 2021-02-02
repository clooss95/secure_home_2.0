package com.bonacode.securehome.domain.feature.action.usecase.partition

import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.application.smscommunication.builder.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.BuildActionUseCase
import javax.inject.Inject

class BuildStopAlarmPartitionAction @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : BuildActionUseCase<BuildStopAlarmPartitionAction.Params, ActionModel> {

    override val actionType: ActionType = ActionType.STOP_ALARM_PARTITION

    override suspend fun invoke(params: Params): ActionModel = BaseActionModel(
        actionType, actionCommandBuilderProvider.provideActionCommandBuilder()
            .stopAlarmPartition(params.partition)
    )

    data class Params(
        val partition: Int
    )
}
