package com.bonacode.securehome.architecture.mvvm

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bonacode.securehome.architecture.SingleEvent
import com.bonacode.securehome.architecture.navigation.NavigationAction

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    private val _navigationEvent = MutableLiveData<SingleEvent<NavigationAction>>()
    val navigationEvent: LiveData<SingleEvent<NavigationAction>> =
        _navigationEvent

    private val _hideKeyboardEvent = MutableLiveData<SingleEvent<Unit>>()
    val hideKeyboardEvent: LiveData<SingleEvent<Unit>> = _hideKeyboardEvent

    protected fun handleNavigationAction(action: NavigationAction) {
        _navigationEvent.postValue(SingleEvent(action))
    }

    protected fun hideKeyboard() {
        _hideKeyboardEvent.postValue(SingleEvent(Unit))
    }
}
