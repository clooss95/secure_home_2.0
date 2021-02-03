package com.bonacode.securehome.domain.feature.actionhistory.usecase

import androidx.paging.PagingData
import com.bonacode.securehome.domain.common.FlowUseCase
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import com.bonacode.securehome.domain.feature.actionhistory.model.ActionHistoryModel
import com.bonacode.securehome.domain.feature.actionhistory.model.DateSelectionState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActionHistoryList @Inject constructor(
    private val actionHistoryDataSource: ActionHistoryDataSource
) : FlowUseCase<GetActionHistoryList.Params, PagingData<ActionHistoryModel>> {
    override fun invoke(params: Params): Flow<PagingData<ActionHistoryModel>> =
        with(params) {
            when (dateSelectionState) {
                is DateSelectionState.NotSelected -> actionHistoryDataSource.getActionHistoryList()
                is DateSelectionState.Selected -> actionHistoryDataSource.getActionHistoryListForDay(
                    dateSelectionState.date
                )
            }
        }

    data class Params(
        val dateSelectionState: DateSelectionState
    )
}
