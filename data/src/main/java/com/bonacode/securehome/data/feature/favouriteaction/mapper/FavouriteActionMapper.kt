package com.bonacode.securehome.data.feature.favouriteaction.mapper

import com.bonacode.securehome.data.common.BaseMapper
import com.bonacode.securehome.data.feature.favouriteaction.entity.FavouriteActionEntity
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.favouriteaction.model.FavouriteActionModel
import javax.inject.Inject

class FavouriteActionMapper @Inject constructor() :
    BaseMapper<FavouriteActionEntity, FavouriteActionModel> {
    override fun transform(input: FavouriteActionEntity): FavouriteActionModel = with(input) {
        FavouriteActionModel(
            id = id,
            actionType = ActionType.valueOf(actionTypeName),
            smsCommand = smsCommand,
            name = name
        )
    }

    override fun reverse(input: FavouriteActionModel): FavouriteActionEntity = with(input) {
        FavouriteActionEntity(
            id = id,
            actionTypeName = actionType.name,
            smsCommand = smsCommand,
            name = name
        )
    }
}
