package com.bonacode.securehome.domain.feature.home.model

import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.favouriteaction.model.FavouriteActionModel

interface HomeItemModelContract {
    val id: Long?
}

sealed class HomeItemModel : ActionModel, HomeItemModelContract {
    data class Default(
        override val id: Long?,
        override val actionType: ActionType,
        override val smsCommand: String
    ) : HomeItemModel()

    data class Favourite(
        val favouriteActionModel: FavouriteActionModel,
        override val id: Long? = favouriteActionModel.id,
        override val actionType: ActionType = favouriteActionModel.actionType,
        override val smsCommand: String = favouriteActionModel.smsCommand,
        val name: String = favouriteActionModel.name
    ) : HomeItemModel()
}
