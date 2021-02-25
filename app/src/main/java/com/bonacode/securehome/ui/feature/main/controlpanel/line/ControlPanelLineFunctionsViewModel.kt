package com.bonacode.securehome.ui.feature.main.controlpanel.line

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.domain.feature.action.usecase.line.BlockLine
import com.bonacode.securehome.domain.feature.action.usecase.line.BuildBlockLineAction
import com.bonacode.securehome.domain.feature.action.usecase.line.BuildUnblockLineAction
import com.bonacode.securehome.domain.feature.action.usecase.line.UnblockLine
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.CanSaveFavouriteAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.SaveFavouriteAction
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.SaveFavouriteActionCallback
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionViewModel
import kotlinx.coroutines.launch

class ControlPanelLineFunctionsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val blockLine: BlockLine,
    private val unblockLine: UnblockLine,
    private val buildBlockLineAction: BuildBlockLineAction,
    private val buildUnblockLineAction: BuildUnblockLineAction,
    private val actionSentCallback: ActionSentCallback,
    canSaveFavouriteAction: CanSaveFavouriteAction,
    saveFavouriteActionCallback: SaveFavouriteActionCallback,
    saveFavouriteAction: SaveFavouriteAction
) : ControlPanelSectionViewModel(
    savedStateHandle,
    saveFavouriteAction,
    canSaveFavouriteAction,
    saveFavouriteActionCallback
) {

    fun blockLineClicked() {
        selectLineAndProcessAction { line ->
            if (isInAddToFavouriteMode) {
                processBlockLineAddToFavourite(line)
            } else {
                processBlockLineCommand(line)
            }
        }
    }

    fun unblockLineClicked() {
        selectLineAndProcessAction { line ->
            if (isInAddToFavouriteMode) {
                processUnblockLineAddToFavourite(line)
            } else {
                processUnblockLineCommand(line)
            }
        }
    }

    private fun processBlockLineAddToFavourite(line: Int) {
        askForActionNameAndSaveFavouriteAction { actionName ->
            viewModelScope.launch {
                saveFavouriteAction(
                    buildBlockLineAction.invoke(
                        BuildBlockLineAction.Params(line)
                    ),
                    actionName
                )
                navigateBack()
            }
        }
    }

    private fun processBlockLineCommand(line: Int) {
        viewModelScope.launch {
            val action = blockLine.invoke(BlockLine.Params(line = line))
            actionSentCallback.actionSent(
                action.second,
                ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                action.first
            )
        }
    }

    private fun processUnblockLineAddToFavourite(line: Int) {
        askForActionNameAndSaveFavouriteAction { actionName ->
            viewModelScope.launch {
                saveFavouriteAction(
                    buildUnblockLineAction.invoke(
                        BuildUnblockLineAction.Params(line)
                    ),
                    actionName
                )
                navigateBack()
            }
        }
    }

    private fun processUnblockLineCommand(line: Int) {
        viewModelScope.launch {
            val action = unblockLine.invoke(UnblockLine.Params(line = line))
            actionSentCallback.actionSent(
                action.second,
                ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                action.first
            )
        }
    }
}
