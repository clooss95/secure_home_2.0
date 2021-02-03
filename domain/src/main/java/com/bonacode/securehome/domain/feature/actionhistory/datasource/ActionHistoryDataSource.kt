package com.bonacode.securehome.domain.feature.actionhistory.datasource

import androidx.paging.PagingData
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.actionhistory.model.ActionHistoryModel
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDate

interface ActionHistoryDataSource {
    suspend fun saveActionHistory(actionType: ActionType, smsCommand: String, favouriteActionId: Long? = null)
    suspend fun clearHistory()
    suspend fun delete(id: Long?)
    fun getActionHistoryList(): Flow<PagingData<ActionHistoryModel>>
    fun getActionHistoryListForDay(day: LocalDate): Flow<PagingData<ActionHistoryModel>>
}
