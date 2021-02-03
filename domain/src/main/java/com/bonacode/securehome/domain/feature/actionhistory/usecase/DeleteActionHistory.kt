package com.bonacode.securehome.domain.feature.actionhistory.usecase

import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.actionhistory.datasource.ActionHistoryDataSource
import javax.inject.Inject

class DeleteActionHistory @Inject constructor(
    private val actionHistoryDataSource: ActionHistoryDataSource
) : UseCase<DeleteActionHistory.Params, Unit> {

    override suspend fun invoke(params: Params) {
        actionHistoryDataSource.delete(params.id)
    }

    data class Params(
        val id: Long?
    )
}
