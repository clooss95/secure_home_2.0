package com.bonacode.securehome.domain.feature.action.model

data class BaseActionModel(
    override val actionType: ActionType,
    override val smsCommand: String
) : ActionModel
