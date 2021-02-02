package com.bonacode.securehome.domain.feature.favouriteaction.datasource

import com.bonacode.securehome.domain.feature.favouriteaction.model.FavouriteActionModel
import kotlinx.coroutines.flow.Flow

interface FavouriteActionDataSource {
    suspend fun saveFavouriteAction(model: FavouriteActionModel)
    fun getFavouriteActions(): Flow<List<FavouriteActionModel>>
    suspend fun disableFavouriteAction(id: Long?)
    suspend fun getFavouriteActionCount(): Long
}
