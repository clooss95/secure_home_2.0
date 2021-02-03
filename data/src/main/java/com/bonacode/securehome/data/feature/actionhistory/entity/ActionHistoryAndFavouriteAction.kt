package com.bonacode.securehome.data.feature.actionhistory.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.bonacode.securehome.data.feature.favouriteaction.entity.FavouriteActionEntity

data class ActionHistoryAndFavouriteAction(
    @Embedded
    var actionHistoryEntity: ActionHistoryEntity? = null,

    @Relation(
        parentColumn = "favouriteActionId",
        entityColumn = "id"
    )
    var favouriteActionEntity: FavouriteActionEntity? = null
)
