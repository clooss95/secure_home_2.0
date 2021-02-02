package com.bonacode.securehome.data.feature.actionhistory.mapper

import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.data.feature.actionhistory.entity.ActionHistoryAndFavouriteAction
import com.bonacode.securehome.data.feature.favouriteaction.mapper.FavouriteActionMapper
import com.bonacode.securehome.domain.feature.actionhistory.model.ActionHistoryModel
import javax.inject.Inject

class ActionHistoryMapper @Inject constructor(
    private val favouriteActionMapper: FavouriteActionMapper
) {

    fun transform(input: ActionHistoryAndFavouriteAction): ActionHistoryModel = with(input) {
        ActionHistoryModel(
            id = actionHistoryEntity!!.id,
            date = actionHistoryEntity!!.date,
            actionType = ActionType.valueOf(actionHistoryEntity!!.actionTypeName),
            smsCommand = actionHistoryEntity!!.smsCommand,
            favouriteActionModel = favouriteActionEntity?.let { favouriteActionMapper.transform(it) }
        )
    }
}
