package com.bonacode.securehome.ui.feature.main.controlpanel.mainfunctions

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.domain.common.invoke
import com.bonacode.securehome.domain.feature.action.usecase.main.BuildStartAlarmAction
import com.bonacode.securehome.domain.feature.action.usecase.main.BuildStartAlarmGroupAction
import com.bonacode.securehome.domain.feature.action.usecase.main.BuildStartAlarmHomeAction
import com.bonacode.securehome.domain.feature.action.usecase.main.BuildStopAlarmAction
import com.bonacode.securehome.domain.feature.action.usecase.main.StartAlarm
import com.bonacode.securehome.domain.feature.action.usecase.main.StartAlarmGroup
import com.bonacode.securehome.domain.feature.action.usecase.main.StartAlarmHome
import com.bonacode.securehome.domain.feature.action.usecase.main.StopAlarm
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.CanSaveFavouriteAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.SaveFavouriteAction
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.SaveFavouriteActionCallback
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionViewModel
import kotlinx.coroutines.launch

class ControlPanelMainFunctionsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val startAlarmNormal: StartAlarm,
    private val startAlarmHome: StartAlarmHome,
    private val stopAlarm: StopAlarm,
    private val startAlarmGroup: StartAlarmGroup,
    private val buildStartAlarmAction: BuildStartAlarmAction,
    private val buildStartAlarmHomeAction: BuildStartAlarmHomeAction,
    private val buildStartAlarmGroupAction: BuildStartAlarmGroupAction,
    private val buildStopAlarmAction: BuildStopAlarmAction,
    private val actionSentCallback: ActionSentCallback,
    saveFavouriteAction: SaveFavouriteAction,
    canSaveFavouriteAction: CanSaveFavouriteAction,
    saveFavouriteActionCallback: SaveFavouriteActionCallback
) : ControlPanelSectionViewModel(savedStateHandle, saveFavouriteAction, canSaveFavouriteAction, saveFavouriteActionCallback) {

    fun startAlarmNormal() {
        if (isInAddToFavouriteMode) {
            askForActionNameAndSaveFavouriteAction { actionName ->
                viewModelScope.launch {
                    saveFavouriteAction(buildStartAlarmAction.invoke(), actionName)
                    navigateBack()
                }
            }
        } else {
            viewModelScope.launch {
                val action = startAlarmNormal.invoke()
                actionSentCallback.actionSent(action.second, ActionSentCallback.ActionSentSource.CONTROL_PANEL, action.first)
            }
        }
    }

    fun startAlarmHome() {
        if (isInAddToFavouriteMode) {
            askForActionNameAndSaveFavouriteAction { actionName ->
                viewModelScope.launch {
                    saveFavouriteAction(buildStartAlarmHomeAction.invoke(), actionName)
                    navigateBack()
                }
            }
        } else {
            viewModelScope.launch {
                val action = startAlarmHome.invoke()
                actionSentCallback.actionSent(action.second, ActionSentCallback.ActionSentSource.CONTROL_PANEL, action.first)
            }
        }
    }

    fun stopAlarm() {
        if (isInAddToFavouriteMode) {
            askForActionNameAndSaveFavouriteAction { actionName ->
                viewModelScope.launch {
                    saveFavouriteAction(buildStopAlarmAction.invoke(), actionName)
                    navigateBack()
                }
            }
        } else {
            viewModelScope.launch {
                val action = stopAlarm.invoke()
                actionSentCallback.actionSent(action.second, ActionSentCallback.ActionSentSource.CONTROL_PANEL, action.first)
            }
        }
    }

    fun startAlarmGroup() {
        selectGroupAndProcessAction { group ->
            if (isInAddToFavouriteMode) {
                askForActionNameAndSaveFavouriteAction { actionName ->
                    viewModelScope.launch {
                        saveFavouriteAction(
                            buildStartAlarmGroupAction.invoke(
                                BuildStartAlarmGroupAction.Params(group)
                            ), actionName
                        )
                        navigateBack()
                    }
                }
            } else {
                viewModelScope.launch {
                    val action = startAlarmGroup.invoke(StartAlarmGroup.Params(group = group))
                    actionSentCallback.actionSent(action.second, ActionSentCallback.ActionSentSource.CONTROL_PANEL, action.first)
                }
            }
        }
    }
}
