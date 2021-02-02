package com.bonacode.securehome.ui.feature.setup.askforpermissions

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bonacode.securehome.application.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.application.common.SingleEvent
import com.bonacode.securehome.domain.feature.settings.datasource.SettingsDataSource

class SetupAskForPermissionsViewModel @ViewModelInject constructor(
    private val settingsDataSource: SettingsDataSource
) : BaseViewModel() {

    private val _askForPermissionsEvent = MutableLiveData<SingleEvent<Unit>>()
    val askForPermissionsEvent: LiveData<SingleEvent<Unit>> = _askForPermissionsEvent

    private val _permissionsDeniedEvent = MutableLiveData<SingleEvent<Unit>>()
    val permissionsDeniedEvent: LiveData<SingleEvent<Unit>> = _permissionsDeniedEvent

    private val _navigateToMainActivityEvent = MutableLiveData<SingleEvent<Unit>>()
    val navigateToMainActivityEvent: LiveData<SingleEvent<Unit>> = _navigateToMainActivityEvent

    fun aksForPermissions() {
        _askForPermissionsEvent.postValue(SingleEvent(Unit))
    }

    fun saveAndNavigateForward() {
        settingsDataSource.onConfigurationDone()
        _navigateToMainActivityEvent.postValue(SingleEvent(Unit))
    }

    fun permissionsDenied() {
        _permissionsDeniedEvent.postValue(SingleEvent(Unit))
    }
}
