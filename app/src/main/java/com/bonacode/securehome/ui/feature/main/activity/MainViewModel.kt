package com.bonacode.securehome.ui.feature.main.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.R
import com.bonacode.securehome.architecture.SingleEvent
import com.bonacode.securehome.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.domain.common.invoke
import com.bonacode.securehome.domain.feature.action.model.ActionModel
import com.bonacode.securehome.domain.feature.action.model.ActionSentEvent
import com.bonacode.securehome.domain.feature.action.model.SmsSendResult
import com.bonacode.securehome.domain.feature.favouriteaction.model.FavouriteActionModel
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.CanSaveFavouriteAction
import com.bonacode.securehome.domain.feature.favouriteaction.usecase.SaveFavouriteAction
import com.bonacode.securehome.domain.feature.settings.usecase.GetSettings
import com.bonacode.securehome.ui.feature.enterpin.PinTextChangedCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.ActionSentCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.PinCodeEnteredCallback
import com.bonacode.securehome.ui.feature.main.activity.callback.SaveFavouriteActionCallback
import com.bonacode.securehome.ui.feature.setup.enterpincode.isApplicationPinCodeValid
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val saveFavouriteAction: SaveFavouriteAction,
    private val getSettings: GetSettings,
    private val canSaveFavouriteAction: CanSaveFavouriteAction
) : BaseViewModel(),
    ActionSentCallback,
    PinCodeEnteredCallback,
    SaveFavouriteActionCallback,
    PinTextChangedCallback {

    val pinCode = MutableLiveData("")
    val pinCodeValid: LiveData<Boolean> = Transformations.map(pinCode) {
        it?.isApplicationPinCodeValid() ?: false
    }
    private val _showEnterPinCodeView = MutableLiveData(false)
    val showEnterPinCodeView: LiveData<Boolean> = _showEnterPinCodeView

    private val _showActionSentEvent = MutableLiveData<SingleEvent<ActionSentEvent>>()
    val showActionSentEvent: LiveData<SingleEvent<ActionSentEvent>> = _showActionSentEvent

    private val _pinErrorText: MutableLiveData<Int?> = MutableLiveData(null)
    val pinErrorText: LiveData<Int?> = _pinErrorText

    private val _favouriteActionsLimitReached = MutableLiveData<SingleEvent<Unit>>()
    val favouriteActionsLimitReached: LiveData<SingleEvent<Unit>> = _favouriteActionsLimitReached

    override fun actionSent(
        action: ActionModel,
        source: ActionSentCallback.ActionSentSource,
        result: SmsSendResult
    ) {
        when (source) {
            ActionSentCallback.ActionSentSource.CONTROL_PANEL -> handleActionSent(
                action = action,
                showAddToFavouriteButton = true,
                result = result
            )
            ActionSentCallback.ActionSentSource.HOME -> handleActionSent(
                action = action,
                showAddToFavouriteButton = false,
                result = result
            )
            ActionSentCallback.ActionSentSource.HISTORY -> handleActionSent(
                action = action,
                showAddToFavouriteButton = false,
                result = result
            )
        }
    }

    fun saveFavouriteAction(action: ActionModel, actionName: String) {
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
                onFavouriteActionsLimitReached()
            }
        }
    }

    override fun onFavouriteActionsLimitReached() {
        _favouriteActionsLimitReached.postValue(
            SingleEvent(
                Unit
            )
        )
    }

    override fun pinCodeEntered() {
        viewModelScope.launch {
            val settings = getSettings.invoke()
            if (pinCode.value == settings.applicationPinCode) {
                pinCode.postValue("")
                _showEnterPinCodeView.postValue(false)
                hideKeyboard()
            } else {
                _pinErrorText.postValue(R.string.wrong_pin)
                if (_pinErrorText.value != null) {
                    pinCode.postValue(null)
                }
            }
        }
    }

    override fun onPinTextChanged() {
        _pinErrorText.postValue(null)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        viewModelScope.launch {
            val settings = getSettings.invoke()
            if (settings.applicationViaPinProtectionEnabled) {
                _showEnterPinCodeView.postValue(true)
            }
        }
    }

    private fun handleActionSent(
        action: ActionModel,
        showAddToFavouriteButton: Boolean,
        result: SmsSendResult
    ) {
        _showActionSentEvent.postValue(
            SingleEvent(
                ActionSentEvent(
                    showAddToFavouriteButton = showAddToFavouriteButton,
                    action = action,
                    result = result
                )
            )
        )
    }
}
