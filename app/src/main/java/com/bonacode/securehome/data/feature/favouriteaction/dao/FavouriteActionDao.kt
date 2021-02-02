package com.bonacode.securehome.data.feature.favouriteaction.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonacode.securehome.data.feature.favouriteaction.entity.FavouriteActionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteActionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavouriteActionEntity)

    @Query("SELECT * FROM favouriteActionEntity WHERE enabled = :enabled")
    fun getAll(enabled: Boolean = true): Flow<List<FavouriteActionEntity>>

    @Query("SELECT * FROM favouriteActionEntity WHERE id = :id")
    suspend fun get(id: Long?): FavouriteActionEntity

    @Query("SELECT COUNT(*) FROM favouriteActionEntity WHERE enabled = :enabled")
    suspend fun getFavouriteActionCount(enabled: Boolean = true): Long
}
