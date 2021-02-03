package com.bonacode.securehome.domain.feature.action.model

interface ActionModel {
    val actionType: ActionType
    val smsCommand: String
}
