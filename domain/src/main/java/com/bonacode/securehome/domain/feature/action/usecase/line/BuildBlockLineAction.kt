package com.bonacode.securehome.domain.feature.action.usecase.line

import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.action.model.BaseActionModel
import com.bonacode.securehome.domain.feature.action.usecase.BuildActionUseCase
import javax.inject.Inject

class BuildBlockLineAction @Inject constructor(
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : BuildActionUseCase<BuildBlockLineAction.Params, ActionModel> {

    override val actionType: ActionType = ActionType.BLOCK_LINE

    override suspend fun invoke(params: Params): ActionModel = BaseActionModel(
        actionType, actionCommandBuilderProvider.provideActionCommandBuilder()
            .blockLine(params.line)
    )

    data class Params(
        val line: Int
    )
}
