package com.bonacode.securehome.data.feature.actionhistory.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bonacode.securehome.application.common.TimeHelper
import com.bonacode.securehome.application.config.DEFAULT_PAGE_SIZE_FOR_PAGINATION
import com.bonacode.securehome.application.smscommunication.ActionType
import com.bonacode.securehome.data.feature.actionhistory.dao.ActionHistoryDao
import com.bonacode.securehome.data.feature.actionhistory.entity.ActionHistoryEntity
import com.bonacode.securehome.data.feature.actionhistory.mapper.ActionHistoryMapper
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import com.bonacode.securehome.domain.feature.actionhistory.model.ActionHistoryModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class ActionHistoryRepository @Inject constructor(
    private val dao: ActionHistoryDao,
    private val actionHistoryMapper: ActionHistoryMapper
) : ActionHistoryDataSource {

    private val pagingConfig: PagingConfig =
        PagingConfig(pageSize = DEFAULT_PAGE_SIZE_FOR_PAGINATION, enablePlaceholders = true)

    override suspend fun saveActionHistory(
        actionType: ActionType,
        smsCommand: String,
        favouriteActionId: Long?
    ) = dao.insert(
        ActionHistoryEntity(
            date = Instant.now(),
            actionTypeName = actionType.name,
            smsCommand = smsCommand,
            favouriteActionId = favouriteActionId
        )
    )

    override suspend fun clearHistory() {
        dao.deleteAll()
    }

    override suspend fun delete(id: Long?) {
        dao.delete(id)
    }

    override fun getActionHistoryList(): Flow<PagingData<ActionHistoryModel>> = Pager(
        config = pagingConfig,
        pagingSourceFactory = { dao.getAllPaged() }
    ).flow.map { pagingData ->
        pagingData.map { entity -> actionHistoryMapper.transform(entity) }
    }

    override fun getActionHistoryListForDay(day: LocalDate): Flow<PagingData<ActionHistoryModel>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                dao.getForDayPaged(
                    dayStart = day.atStartOfDay(TimeHelper.getTimeZone()).toInstant(),
                    dayEnd = day.atTime(LocalTime.MAX).atZone(TimeHelper.getTimeZone()).toInstant()
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { entity -> actionHistoryMapper.transform(entity) }
        }
}
