package com.bonacode.securehome.domain.feature.action.model

import com.bonacode.securehome.application.smscommunication.ActionType

data class BaseActionModel(
    override val actionType: ActionType,
    override val smsCommand: String
) : ActionModel
