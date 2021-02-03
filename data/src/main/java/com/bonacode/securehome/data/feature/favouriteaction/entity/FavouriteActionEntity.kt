package com.bonacode.securehome.data.feature.favouriteaction.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favouriteActionEntity")
data class FavouriteActionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val actionTypeName: String,
    val smsCommand: String,
    val name: String,
    val enabled: Boolean = true
)
