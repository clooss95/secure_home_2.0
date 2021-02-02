package com.bonacode.securehome.domain.feature.favouriteaction.usecase

import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.favouriteaction.datasource.FavouriteActionDataSource
import javax.inject.Inject

class DisableFavouriteAction @Inject constructor(
    private val favouriteActionDataSource: FavouriteActionDataSource
) : UseCase<DisableFavouriteAction.Params, Unit> {

    override suspend fun invoke(params: Params) {
        favouriteActionDataSource.disableFavouriteAction(params.id)
    }

    data class Params(
        val id: Long?
    )
}
