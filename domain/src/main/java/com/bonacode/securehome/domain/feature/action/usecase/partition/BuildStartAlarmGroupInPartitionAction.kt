package com.bonacode.securehome.domain.feature.action.usecase.partition

import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.BuildActionUseCase
import javax.inject.Inject

class BuildStartAlarmGroupInPartitionAction @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : BuildActionUseCase<BuildStartAlarmGroupInPartitionAction.Params, ActionModel> {

    override val actionType: ActionType = ActionType.START_ALARM_GROUP_IN_PARTITION

    override suspend fun invoke(params: Params): ActionModel = BaseActionModel(
        actionType, actionCommandBuilderProvider.provideActionCommandBuilder()
            .startAlarmGroupInPartition(params.group, params.partition)
    )

    data class Params(
        val group: String,
        val partition: Int
    )
}
