package com.bonacode.securehome.ui.feature.main.controlpanel.system

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.domain.common.invoke
import com.bonacode.securehome.domain.feature.action.usecase.system.BuildCheckLastAlarmAction
import com.bonacode.securehome.domain.feature.action.usecase.system.BuildCheckStatusAction
import com.bonacode.securehome.domain.feature.action.usecase.system.BuildSimCreditAction
import com.bonacode.securehome.domain.feature.action.usecase.system.CheckLastAlarm
import com.bonacode.securehome.domain.feature.action.usecase.system.CheckSimCredit
import com.bonacode.securehome.domain.feature.action.usecase.system.CheckSystemStatus
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.CanSaveFavouriteAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.SaveFavouriteAction
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.SaveFavouriteActionCallback
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionViewModel
import kotlinx.coroutines.launch

class ControlPanelSystemFunctionsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val checkSystemStatus: CheckSystemStatus,
    private val checkLastAlarm: CheckLastAlarm,
    private val checkSimCredit: CheckSimCredit,
    private val buildCheckLastAlarmAction: BuildCheckLastAlarmAction,
    private val buildCheckStatusAction: BuildCheckStatusAction,
    private val buildCheckSimCreditAction: BuildSimCreditAction,
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

    fun checkSystemStatusClicked() {
        if (isInAddToFavouriteMode) {
            processCheckSystemStatusAddToFavourite()
        } else {
            processCheckSystemStatusCommand()
        }
    }

    fun checkLastAlarmClicked() {
        if (isInAddToFavouriteMode) {
            processCheckLastAlarmAddToFavourite()
        } else {
            processCheckLastAlarmCommand()
        }
    }

    fun checkSimCreditClicked() {
        if (isInAddToFavouriteMode) {
            processCheckSimCreditAddToFavourite()
        } else {
            processCheckSimCreditCommand()
        }
    }

    private fun processCheckSystemStatusAddToFavourite() {
        askForActionNameAndSaveFavouriteAction { actionName ->
            viewModelScope.launch {
                saveFavouriteAction(buildCheckStatusAction.invoke(), actionName)
                navigateBack()
            }
        }
    }

    private fun processCheckSystemStatusCommand() {
        viewModelScope.launch {
            val action = checkSystemStatus.invoke()
            actionSentCallback.actionSent(
                action.second,
                ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                action.first
            )
        }
    }

    private fun processCheckLastAlarmAddToFavourite() {
        askForActionNameAndSaveFavouriteAction { actionName ->
            viewModelScope.launch {
                saveFavouriteAction(buildCheckLastAlarmAction.invoke(), actionName)
                navigateBack()
            }
        }
    }

    private fun processCheckLastAlarmCommand() {
        viewModelScope.launch {
            val action = checkLastAlarm.invoke()
            actionSentCallback.actionSent(
                action.second,
                ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                action.first
            )
        }
    }

    private fun processCheckSimCreditAddToFavourite() {
        askForActionNameAndSaveFavouriteAction { actionName ->
            viewModelScope.launch {
                saveFavouriteAction(buildCheckSimCreditAction.invoke(), actionName)
                navigateBack()
            }
        }
    }

    private fun processCheckSimCreditCommand() {
        viewModelScope.launch {
            val action = checkSimCredit.invoke()
            actionSentCallback.actionSent(
                action.second,
                ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                action.first
            )
        }
    }
}
