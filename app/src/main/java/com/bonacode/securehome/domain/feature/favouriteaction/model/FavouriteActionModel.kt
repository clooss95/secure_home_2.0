package com.bonacode.securehome.domain.feature.favouriteaction.model

import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.domain.feature.action.model.ActionModel

data class FavouriteActionModel(
    var id: Long? = null,
    override val actionType: ActionType,
    override val smsCommand: String,
    val name: String
) : ActionModel
