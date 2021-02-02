package com.bonacode.securehome.domain.feature.action.usecase.entry

import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.application.smscommunication.builder.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.BuildActionUseCase
import javax.inject.Inject

class BuildInactivateEntryAction @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : BuildActionUseCase<BuildInactivateEntryAction.Params, ActionModel> {

    override val actionType: ActionType = ActionType.INACTIVATE_ENTRY

    override suspend fun invoke(params: Params): ActionModel = BaseActionModel(
        actionType, actionCommandBuilderProvider.provideActionCommandBuilder()
            .inactivateEntry(params.entry)
    )

    data class Params(
        val entry: Int
    )
}
