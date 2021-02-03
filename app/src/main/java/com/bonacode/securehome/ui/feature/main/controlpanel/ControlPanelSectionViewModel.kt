package com.bonacode.securehome.ui.feature.main.controlpanel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.architecture.SingleEvent
import com.bonacode.securehome.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.architecture.navigation.NavigationAction
import com.bonacode.securehome.domain.common.invoke
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.favouriteaction.model.FavouriteActionModel
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.CanSaveFavouriteAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.SaveFavouriteAction
import com.bonacode.securehome.ui.feature.main.activity.callback.SaveFavouriteActionCallback
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionFragment.Companion.ARG_IS_IN_ADD_TO_FAVOURITE_MODE
import kotlinx.coroutines.launch

abstract class ControlPanelSectionViewModel(
    savedStateHandle: SavedStateHandle,
    private val saveFavouriteAction: SaveFavouriteAction,
    private val canSaveFavouriteAction: CanSaveFavouriteAction,
    private val saveFavouriteActionCallback: SaveFavouriteActionCallback
) : BaseViewModel() {

    private val _selectGroupEvent = MutableLiveData<SingleEvent<(String) -> Unit>>()
    val selectGroupEvent: LiveData<SingleEvent<(String) -> Unit>> = _selectGroupEvent

    private val _selectPartitionEvent = MutableLiveData<SingleEvent<(Int) -> Unit>>()
    val selectPartitionEvent: LiveData<SingleEvent<(Int) -> Unit>> = _selectPartitionEvent

    private val _selectLineEvent = MutableLiveData<SingleEvent<(Int) -> Unit>>()
    val selectLineEvent: LiveData<SingleEvent<(Int) -> Unit>> = _selectLineEvent

    private val _selectEntryEvent = MutableLiveData<SingleEvent<(Int) -> Unit>>()
    val selectEntryEvent: LiveData<SingleEvent<(Int) -> Unit>> = _selectEntryEvent

    private val _askForActionNameAndSaveFavActionEvent =
        MutableLiveData<SingleEvent<(String) -> Unit>>()
    val askForActionNameAndSaveFavActionEvent: LiveData<SingleEvent<(String) -> Unit>> =
        _askForActionNameAndSaveFavActionEvent

    protected val isInAddToFavouriteMode: Boolean =
        savedStateHandle.get<Boolean>(ARG_IS_IN_ADD_TO_FAVOURITE_MODE)
            ?: throw IllegalStateException("ControlPanelSectionFragment must have ARG_IS_IN_ADD_TO_FAVOURITE_MODE argment!")

    fun selectGroupAndProcessAction(action: (String) -> Unit) {
        _selectGroupEvent.postValue(SingleEvent(action))
    }

    fun selectPartitionAndProcessAction(action: (Int) -> Unit) {
        _selectPartitionEvent.postValue(SingleEvent(action))
    }

    fun selectLineAndProcessAction(action: (Int) -> Unit) {
        _selectLineEvent.postValue(SingleEvent(action))
    }

    fun selectEntryAndProcessAction(action: (Int) -> Unit) {
        _selectEntryEvent.postValue(SingleEvent(action))
    }

    fun askForActionNameAndSaveFavouriteAction(action: (String) -> Unit) {
        _askForActionNameAndSaveFavActionEvent.postValue(
            SingleEvent(
                action
            )
        )
    }

    protected fun saveFavouriteAction(action: ActionModel, actionName: String) {
        viewModelScope.launch {
            if (canSaveFavouriteAction.invoke()) {
                with(action) {
                    saveFavouriteAction.invoke(
                        SaveFavouriteAction.Params(
                            FavouriteActionModel(
                                name = actionName,
                                actionType = actionType,
                                smsCommand = smsCommand
                            )
                        )
                    )
                }
            } else {
                saveFavouriteActionCallback.onFavouriteActionsLimitReached()
            }
        }
    }

    protected fun navigateBack() {
        handleNavigationAction(NavigationAction.NavigateBack)
    }
}
