package com.bonacode.securehome.domain.feature.favouriteaction.usecase

import com.bonacode.securehome.application.config.FAVOURITE_ACTIONS_LIMIT
import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.favouriteaction.datasource.FavouriteActionDataSource
import javax.inject.Inject

class CanSaveFavouriteAction @Inject constructor(
    private val favouriteActionDataSource: FavouriteActionDataSource
) : UseCase<Unit, Boolean> {
    override suspend fun invoke(params: Unit): Boolean =
        favouriteActionDataSource.getFavouriteActionCount() < FAVOURITE_ACTIONS_LIMIT
}
