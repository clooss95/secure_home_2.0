package com.bonacode.securehome.domain.feature.actionhistory.usecase

import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import javax.inject.Inject

class ClearActionHistory @Inject constructor(
    private val actionHistoryDataSource: ActionHistoryDataSource
) : UseCase<Unit, Unit> {

    override suspend fun invoke(params: Unit) {
        actionHistoryDataSource.clearHistory()
    }
}
