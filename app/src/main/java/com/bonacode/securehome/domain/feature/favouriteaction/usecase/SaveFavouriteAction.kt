package com.bonacode.securehome.domain.feature.favouriteaction.usecase

import com.bonacode.securehome.domain.common.UseCase
import com.bonacode.securehome.domain.feature.favouriteaction.datasource.FavouriteActionDataSource
import com.bonacode.securehome.domain.feature.favouriteaction.model.FavouriteActionModel
import javax.inject.Inject

class SaveFavouriteAction @Inject constructor(
    private val favouriteActionDataSource: FavouriteActionDataSource
) : UseCase<SaveFavouriteAction.Params, Unit> {
    override suspend fun invoke(params: Params) =
        favouriteActionDataSource.saveFavouriteAction(params.favouriteAction)

    data class Params(
        val favouriteAction: FavouriteActionModel
    )
}
