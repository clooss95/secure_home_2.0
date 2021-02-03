package com.bonacode.securehome.domain.feature.home.usecase

import com.bonacode.securehome.domain.common.FlowUseCase
import com.bonacode.securehome.domain.feature.action.ActionCommandBuilderProvider
import com.bonacode.securehome.domain.feature.action.model.ActionType
import com.bonacode.securehome.domain.feature.favouriteaction.datasource.FavouriteActionDataSource
import com.bonacode.securehome.domain.feature.home.model.HomeItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHomeList @Inject constructor(
    private val favouriteActionDataSource: FavouriteActionDataSource,
    private val actionCommandBuilderProvider: ActionCommandBuilderProvider
) : FlowUseCase<Unit, List<HomeItemModel>> {
    override fun invoke(params: Unit): Flow<List<HomeItemModel>> =
        favouriteActionDataSource.getFavouriteActions().map { favourites ->
            val result = mutableListOf<HomeItemModel>()

            // adds default start alarm action
            result.add(
                HomeItemModel.Default(
                    id = -2,
                    actionType = ActionType.START_ALARM,
                    smsCommand = actionCommandBuilderProvider.provideActionCommandBuilder()
                        .startAlarm()

                )
            )

            // adds default stop alarm action
            result.add(
                HomeItemModel.Default(
                    id = -1,
                    actionType = ActionType.STOP_ALARM,
                    smsCommand = actionCommandBuilderProvider.provideActionCommandBuilder()
                        .stopAlarm()
                )
            )

            // adds favourite actions
            result.addAll(favourites.map { favourite ->
                HomeItemModel.Favourite(favourite)
            })

            result
        }
}
