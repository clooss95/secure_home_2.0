package com.bonacode.securehome.ui.feature.main.controlpanel.entry

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.domain.feature.action.usecase.entry.ActivateEntry
import com.bonacode.securehome.domain.feature.action.usecase.entry.BuildActivateEntryAction
import com.bonacode.securehome.domain.feature.action.usecase.entry.BuildInactivateEntryAction
import com.bonacode.securehome.domain.feature.action.usecase.entry.InactivateEntry
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.CanSaveFavouriteAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.SaveFavouriteAction
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.SaveFavouriteActionCallback
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionViewModel
import kotlinx.coroutines.launch

class ControlPanelEntryFunctionsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val activateEntry: ActivateEntry,
    private val inactivateEntry: InactivateEntry,
    private val buildActivateEntryAction: BuildActivateEntryAction,
    private val buildInactivateEntryAction: BuildInactivateEntryAction,
    private val actionSentCallback: ActionSentCallback,
    saveFavouriteAction: SaveFavouriteAction,
    canSaveFavouriteAction: CanSaveFavouriteAction,
    saveFavouriteActionCallback: SaveFavouriteActionCallback
) : ControlPanelSectionViewModel(
    savedStateHandle,
    saveFavouriteAction,
    canSaveFavouriteAction,
    saveFavouriteActionCallback
) {

    fun activateEntry() {
        selectEntryAndProcessAction { entry ->
            if (isInAddToFavouriteMode) {
                askForActionNameAndSaveFavouriteAction { actionName ->
                    viewModelScope.launch {
                        saveFavouriteAction(
                            buildActivateEntryAction.invoke(
                                BuildActivateEntryAction.Params(
                                    entry
                                )
                            ), actionName
                        )
                        navigateBack()
                    }
                }
            } else {
                viewModelScope.launch {
                    val action = activateEntry.invoke(ActivateEntry.Params(entry = entry))
                    actionSentCallback.actionSent(
                        action.second,
                        ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                        action.first
                    )
                }
            }
        }
    }

    fun inactivateEntry() {
        selectEntryAndProcessAction { entry ->
            if (isInAddToFavouriteMode) {
                askForActionNameAndSaveFavouriteAction { actionName ->
                    viewModelScope.launch {
                        saveFavouriteAction(
                            buildInactivateEntryAction.invoke(
                                BuildInactivateEntryAction.Params(entry)
                            ), actionName
                        )
                        navigateBack()
                    }
                }
            } else {
                viewModelScope.launch {
                    val action = inactivateEntry.invoke(InactivateEntry.Params(entry = entry))
                    actionSentCallback.actionSent(
                        action.second,
                        ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                        action.first
                    )
                }
            }
        }
    }
}
