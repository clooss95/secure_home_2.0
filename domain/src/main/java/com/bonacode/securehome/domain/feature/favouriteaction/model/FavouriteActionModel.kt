package com.bonacode.securehome.domain.feature.favouriteaction.model

import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType

data class FavouriteActionModel(
    var id: Long? = null,
    override val actionType: ActionType,
    override val smsCommand: String,
    val name: String
) : ActionModel
