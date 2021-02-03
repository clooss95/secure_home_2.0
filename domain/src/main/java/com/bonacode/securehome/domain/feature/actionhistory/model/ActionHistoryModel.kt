package com.bonacode.securehome.domain.feature.actionhistory.model

import com.bonacode.securehome.application.config.PIN_CODE_PATTERN
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.favouriteaction.model.FavouriteActionModel
import org.threeten.bp.Instant

data class ActionHistoryModel(
    val id: Long?,
    val date: Instant,
    override val actionType: ActionType,
    override val smsCommand: String,
    val favouriteActionModel: FavouriteActionModel? = null
) : ActionModel {
    val smsCommandHumanReadable = smsCommand.removePrefix(PIN_CODE_PATTERN)
}
