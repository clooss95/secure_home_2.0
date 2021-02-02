package com.bonacode.securehome.domain.feature.action.model

import com.bonacode.securehome.application.smscommunication.ActionType

interface ActionModel {
    val actionType: ActionType
    val smsCommand: String
}
