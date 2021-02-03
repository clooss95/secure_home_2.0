package com.bonacode.securehome.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bonacode.securehome.application.config.DATABASE_VERSION
import com.bonacode.securehome.data.feature.actionhistory.dao.ActionHistoryDao
import com.bonacode.securehome.data.feature.actionhistory.entity.ActionHistoryEntity
import com.bonacode.securehome.data.feature.favouriteaction.dao.FavouriteActionDao
import com.bonacode.securehome.data.feature.favouriteaction.entity.FavouriteActionEntity

@Database(
    entities = [
        ActionHistoryEntity::class,
        FavouriteActionEntity::class
    ],
    version = DATABASE_VERSION
)
@TypeConverters(DateConverters::class)
abstract class MyRoomDatabase : RoomDatabase() {
    abstract fun actionHistoryDao(): ActionHistoryDao
    abstract fun favouriteActionDao(): FavouriteActionDao
}
