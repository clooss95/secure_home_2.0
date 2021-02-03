package com.bonacode.securehome.ui.feature.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.R
import com.bonacode.securehome.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.architecture.navigation.NavigationAction
import com.bonacode.securehome.domain.common.invoke
import com.bonacode.securehome.domain.feature.action.usecase.SendDefaultAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.DisableFavouriteAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.SendFavouriteAction
import com.bonacode.securehome.domain.feature.home.model.HomeItemModel
import com.bonacode.securehome.domain.feature.home.usecase.GetHomeList
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val sendFavouriteAction: SendFavouriteAction,
    private val sendDefaultAction: SendDefaultAction,
    private val actionSentCallback: ActionSentCallback,
    private val disableFavouriteAction: DisableFavouriteAction,
    getHomeList: GetHomeList
) : BaseViewModel() {

    val homeActions: LiveData<List<HomeItemModel>> =
        getHomeList.invoke().distinctUntilChanged()
            .asLiveData(viewModelScope.coroutineContext)

    fun navigateToAddToFavourites() {
        handleNavigationAction(
            NavigationAction.NavigateTo(
                R.id.action_homeFragment_to_addToFavouriteFragment
            )
        )
    }

    fun sendAction(action: HomeItemModel) {
        viewModelScope.launch {
            val result = when (action) {
                is HomeItemModel.Favourite -> sendFavouriteAction.invoke(SendFavouriteAction.Params(action.favouriteActionModel))
                is HomeItemModel.Default -> sendDefaultAction.invoke(SendDefaultAction.Params(action))
            }
            actionSentCallback.actionSent(
                action = action,
                source = ActionSentCallback.ActionSentSource.HOME,
                result = result
            )
        }
    }

    fun disableFavouriteAction(favouriteActionId: Long) {
        viewModelScope.launch {
            disableFavouriteAction.invoke(DisableFavouriteAction.Params(favouriteActionId))
        }
    }
}
