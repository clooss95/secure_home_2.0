package com.bonacode.securehome.ui.feature.main.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.application.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.application.architecture.navigation.NavigationAction
import com.bonacode.securehome.application.common.combineWith
import com.bonacode.securehome.domain.common.invoke
import com.bonacode.securehome.domain.feature.settings.model.SettingsModel
import com.bonacode.securehome.domain.feature.settings.usecase.GetSettings
import com.bonacode.securehome.domain.feature.settings.usecase.UpdateSettings
import com.bonacode.securehome.ui.feature.setup.enterphonenumber.isValidPhoneNumber
import com.bonacode.securehome.ui.feature.setup.enterpincode.isAlarmPinCodeValid
import com.bonacode.securehome.ui.feature.setup.enterpincode.isApplicationPinCodeValid
import kotlinx.coroutines.launch

class SettingsViewModel @ViewModelInject constructor(
    private val getSettings: GetSettings,
    private val updateSettings: UpdateSettings
) : BaseViewModel() {

    val phoneNumber = MutableLiveData<String>()
    val pinCode = MutableLiveData<String>()
    val applicationPinCode = MutableLiveData<String>()
    val applicationViaPinProtectionEnabled = MutableLiveData<Boolean>()

    val applicationPinCodeVisible = Transformations.map(applicationViaPinProtectionEnabled) {
        it == true
    }

    val saveButtonEnabled: LiveData<Boolean> = phoneNumber.combineWith(
        pinCode,
        applicationViaPinProtectionEnabled,
        applicationPinCode
    ) { phoneNumber, pinCode, protectApplicationViaPin, applicationPinCode ->
        (phoneNumber?.isValidPhoneNumber() ?: false) && (pinCode?.isAlarmPinCodeValid() ?: false) &&
                if (protectApplicationViaPin == true) {
                    applicationPinCode?.isApplicationPinCodeValid() ?: false
                } else {
                    true
                }
    }

    init {
        viewModelScope.launch {
            val settings = getSettings.invoke()

            phoneNumber.postValue(settings.phoneNumber)
            pinCode.postValue(settings.pinCode)
            applicationPinCode.postValue(settings.applicationPinCode)
            applicationViaPinProtectionEnabled.postValue(settings.applicationViaPinProtectionEnabled)
        }
    }

    fun save() {
        viewModelScope.launch {
            updateSettings.invoke(
                UpdateSettings.Params(
                    SettingsModel(
                        phoneNumber = phoneNumber.value!!,
                        pinCode = pinCode.value!!,
                        applicationPinCode = applicationPinCode.value!!,
                        applicationViaPinProtectionEnabled = applicationViaPinProtectionEnabled.value!!
                    )
                )
            )

            hideKeyboard()
            handleNavigationAction(NavigationAction.NavigateBack)
        }
    }
}
