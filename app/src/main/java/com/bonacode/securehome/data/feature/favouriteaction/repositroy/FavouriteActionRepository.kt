package com.bonacode.securehome.data.feature.favouriteaction.repositroy

import com.bonacode.securehome.data.feature.favouriteaction.dao.FavouriteActionDao
import com.bonacode.securehome.data.feature.favouriteaction.mapper.FavouriteActionMapper
import com.bonacode.securehome.domain.feature.favouriteaction.datasource.FavouriteActionDataSource
import com.bonacode.securehome.domain.feature.favouriteaction.model.FavouriteActionModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FavouriteActionRepository @Inject constructor(
    private val dao: FavouriteActionDao,
    private val favouriteActionMapper: FavouriteActionMapper
) : FavouriteActionDataSource {

    override suspend fun saveFavouriteAction(model: FavouriteActionModel) =
        dao.insert(favouriteActionMapper.reverse(model))

    override fun getFavouriteActions(): Flow<List<FavouriteActionModel>> =
        dao.getAll().map { list ->
            favouriteActionMapper.transform(list)
        }.flowOn(Dispatchers.IO).conflate()

    override suspend fun disableFavouriteAction(id: Long?) {
        val entity = dao.get(id)
        dao.insert(entity.copy(enabled = false))
    }

    override suspend fun getFavouriteActionCount(): Long =
        dao.getFavouriteActionCount()
}
