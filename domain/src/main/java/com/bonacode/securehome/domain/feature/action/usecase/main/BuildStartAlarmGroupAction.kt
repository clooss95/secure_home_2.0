package com.bonacode.securehome.domain.feature.action.usecase.main

import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.BuildActionUseCase
import javax.inject.Inject

class BuildStartAlarmGroupAction @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : BuildActionUseCase<BuildStartAlarmGroupAction.Params, ActionModel> {

    override val actionType: ActionType = ActionType.START_ALARM_GROUP

    override suspend fun invoke(params: Params): ActionModel = BaseActionModel(
        actionType, actionCommandBuilderProvider.provideActionCommandBuilder()
            .startAlarmGroup(params.group)
    )

    data class Params(
        val group: String
    )
}
