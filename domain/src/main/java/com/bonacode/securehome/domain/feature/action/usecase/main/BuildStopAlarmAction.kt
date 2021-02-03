package com.bonacode.securehome.domain.feature.action.usecase.main

import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.BuildActionUseCase
import javax.inject.Inject

class BuildStopAlarmAction @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : BuildActionUseCase<Unit, ActionModel> {

    override val actionType: ActionType = ActionType.STOP_ALARM

    override suspend fun invoke(params: Unit): ActionModel = BaseActionModel(
        actionType, actionCommandBuilderProvider.provideActionCommandBuilder()
            .stopAlarm()
    )
}
