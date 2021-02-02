package com.bonacode.securehome.domain.feature.action.model

import com.bonacode.securehome.application.smscommunication.SmsSendResult

data class ActionSentEvent(
    val showAddToFavouriteButton: Boolean,
    val action: ActionModel,
    val result: SmsSendResult
)
