package com.bonacode.securehome.ui.feature.main.controlpanel.partition

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.domain.feature.action.usecase.partition.BuildStartAlarmGroupInPartitionAction
import com.bonacode.securehome.domain.feature.action.usecase.partition.BuildStartAlarmPartitionAction
import com.bonacode.securehome.domain.feature.action.usecase.partition.BuildStartAlarmPartitionHomeAction
import com.bonacode.securehome.domain.feature.action.usecase.partition.BuildStopAlarmPartitionAction
import com.bonacode.securehome.domain.feature.action.usecase.partition.StartAlarmGroupInPartition
import com.bonacode.securehome.domain.feature.action.usecase.partition.StartAlarmPartition
import com.bonacode.securehome.domain.feature.action.usecase.partition.StartAlarmPartitionHome
import com.bonacode.securehome.domain.feature.action.usecase.partition.StopAlarmPartition
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.CanSaveFavouriteAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.SaveFavouriteAction
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.SaveFavouriteActionCallback
import com.bonacode.securehome.ui.feature.main.controlpanel.ControlPanelSectionViewModel
import kotlinx.coroutines.launch

class ControlPanelPartitionFunctionsViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val startAlarmPartition: StartAlarmPartition,
    private val startAlarmPartitionHome: StartAlarmPartitionHome,
    private val startAlarmGroupInPartition: StartAlarmGroupInPartition,
    private val stopAlarmPartition: StopAlarmPartition,
    private val buildStartAlarmPartitionAction: BuildStartAlarmPartitionAction,
    private val buildStartAlarmPartitionHomeAction: BuildStartAlarmPartitionHomeAction,
    private val buildStartAlarmGroupInPartitionAction: BuildStartAlarmGroupInPartitionAction,
    private val buildStopAlarmPartitionAction: BuildStopAlarmPartitionAction,
    private val actionSentCallback: ActionSentCallback,
    canSaveFavouriteAction: CanSaveFavouriteAction,
    saveFavouriteActionCallback: SaveFavouriteActionCallback,
    saveFavouriteAction: SaveFavouriteAction
) : ControlPanelSectionViewModel(savedStateHandle, saveFavouriteAction, canSaveFavouriteAction, saveFavouriteActionCallback) {

    fun startAlarmPartition() {
        selectPartitionAndProcessAction { partition ->
            if (isInAddToFavouriteMode) {
                askForActionNameAndSaveFavouriteAction { actionName ->
                    viewModelScope.launch {
                        saveFavouriteAction(
                            buildStartAlarmPartitionAction.invoke(
                                BuildStartAlarmPartitionAction.Params(partition)
                            ), actionName
                        )
                        navigateBack()
                    }
                }
            } else {
                viewModelScope.launch {
                    val action = startAlarmPartition.invoke(
                        StartAlarmPartition.Params(
                            partition = partition
                        )
                    )
                    actionSentCallback.actionSent(
                        action.second,
                        ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                        action.first
                    )
                }
            }
        }
    }

    fun startAlarmPartitionHome() {
        selectPartitionAndProcessAction { partition ->
            if (isInAddToFavouriteMode) {
                askForActionNameAndSaveFavouriteAction { actionName ->
                    viewModelScope.launch {
                        saveFavouriteAction(
                            buildStartAlarmPartitionHomeAction.invoke(
                                BuildStartAlarmPartitionHomeAction.Params(partition)
                            ), actionName
                        )
                        navigateBack()
                    }
                }
            } else {
                viewModelScope.launch {
                    val action =
                        startAlarmPartitionHome.invoke(StartAlarmPartitionHome.Params(partition = partition))
                    actionSentCallback.actionSent(
                        action.second,
                        ActionSentCallback.ActionSentSource.CONTROL_PANEL,
                        action.first
                    )
                }
            }
        }
    }

    fun startAlarmGroupInPartition() {
        selectGroupAndProcessAction { group ->
            selectPartitionAndProcessAction { partition ->
                if (isInAddToFavouriteMode) {
                    askForActionNameAndSaveFavouriteAction { actionName ->
                        viewModelScope.launch {
                            saveFavouriteAction(
                                buildStartAlarmGroupInPartitionAction.invoke(
                                    BuildStartAlarmGroupInPartitionAction.Params(group, partition)
                                ), actionName
                            )
                            navigateBack()
                        }
                    }
                } else {
                    viewModelScope.launch {
                        val action = startAlarmGroupInPartition.invoke(
                            StartAlarmGroupInPartition.Params(
                                group = group,
                                partition = partition
                            )
                        )
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

    fun stopAlarmPartition() {
        selectPartitionAndProcessAction { partition ->
            if (isInAddToFavouriteMode) {
                askForActionNameAndSaveFavouriteAction { actionName ->
                    viewModelScope.launch {
                        saveFavouriteAction(
                            buildStopAlarmPartitionAction.invoke(
                                BuildStopAlarmPartitionAction.Params(
                                    partition
                                )
                            ), actionName
                        )
                        navigateBack()
                    }
                }
            } else {
                viewModelScope.launch {
                    val action =
                        stopAlarmPartition.invoke(StopAlarmPartition.Params(partition = partition))
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
