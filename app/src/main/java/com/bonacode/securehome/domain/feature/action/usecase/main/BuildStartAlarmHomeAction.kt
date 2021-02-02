package com.bonacode.securehome.domain.feature.action.usecase.main

import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.application.smscommunication.builder.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.BuildActionUseCase
import javax.inject.Inject

class BuildStartAlarmHomeAction @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : BuildActionUseCase<Unit, ActionModel> {

    override val actionType: ActionType = ActionType.START_ALARM_HOME

    override suspend fun invoke(params: Unit): ActionModel = BaseActionModel(
        actionType, actionCommandBuilderProvider.provideActionCommandBuilder()
            .startAlarmHome()
    )
}
