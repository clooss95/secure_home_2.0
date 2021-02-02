package com.bonacode.securehome.domain.feature.action.usecase.system

import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.application.smscommunication.builder.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.BuildActionUseCase
import javax.inject.Inject

class BuildSimCreditAction @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : BuildActionUseCase<Unit, ActionModel> {

    override val actionType: ActionType = ActionType.CHECK_SIM_CREDIT

    override suspend fun invoke(params: Unit): ActionModel = BaseActionModel(
        actionType, actionCommandBuilderProvider.provideActionCommandBuilder()
            .checkSimCredit()
    )
}