package com.bonacode.securehome.ui.feature.setup.enterpincode

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.bonacode.securehome.R
import com.bonacode.securehome.application.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.application.architecture.navigation.NavigationAction
import com.bonacode.securehome.domain.common.invoke
import com.bonacode.securehome.domain.feature.settings.usecase.GetSettings
import com.bonacode.securehome.domain.feature.settings.usecase.UpdateSettings
import kotlinx.coroutines.launch

class SetupEnterPinCodeViewModel @ViewModelInject constructor(
    private val getSettings: GetSettings,
    private val updateSettings: UpdateSettings
) : BaseViewModel() {

    val pinCode = MutableLiveData<String>()

    val formValid: LiveData<Boolean> = Transformations.map(pinCode) {
        it?.isAlarmPinCodeValid() ?: false
    }

    fun saveAndNavigateForward() {
        viewModelScope.launch {
            val settings = getSettings.invoke()
            updateSettings.invoke(
                UpdateSettings.Params(
                    settings.copy(
                        pinCode = pinCode.value!!
                    )
                )
            )

            handleNavigationAction(
                NavigationAction.NavigateTo(
                    R.id.action_setupEnterPinCodeFragment_to_setupAskForPermissionsFragment
                )
            )
        }
    }
}
