package com.bonacode.securehome.domain.feature.action.model

data class ActionSentEvent(
    val showAddToFavouriteButton: Boolean,
    val action: ActionModel,
    val result: SmsSendResult
)
