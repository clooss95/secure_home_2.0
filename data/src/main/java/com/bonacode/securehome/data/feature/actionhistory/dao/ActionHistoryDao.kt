package com.bonacode.securehome.data.feature.actionhistory.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonacode.securehome.data.feature.actionhistory.entity.ActionHistoryAndFavouriteAction
import com.bonacode.securehome.data.feature.actionhistory.entity.ActionHistoryEntity
import org.threeten.bp.Instant

@Dao
interface ActionHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ActionHistoryEntity)

    @Query("DELETE FROM actionHistoryEntity")
    suspend fun deleteAll()

    @Query("DELETE FROM actionHistoryEntity WHERE id = :id")
    suspend fun delete(id: Long?)

    @Query("SELECT * FROM actionHistoryEntity ORDER BY date DESC")
    fun getAllPaged(): PagingSource<Int, ActionHistoryAndFavouriteAction>

    @Query("SELECT * FROM actionHistoryEntity WHERE date BETWEEN :dayStart AND :dayEnd ORDER BY date DESC")
    fun getForDayPaged(dayStart: Instant, dayEnd: Instant): PagingSource<Int, ActionHistoryAndFavouriteAction>
}
