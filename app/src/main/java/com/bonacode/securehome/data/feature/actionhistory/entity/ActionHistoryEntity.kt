package com.bonacode.securehome.data.feature.actionhistory.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.Instant

@Entity(tableName = "actionHistoryEntity")
data class ActionHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val date: Instant,
    val actionTypeName: String,
    val smsCommand: String,
    val favouriteActionId: Long? = null
)
