package com.bonacode.securehome.architecture.mvvm

import androidx.annotation.StringRes
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

    private val _getStringEvent = MutableLiveData<SingleEvent<Pair<Int, (String) -> Unit>>>()
    val getStringEvent: LiveData<SingleEvent<Pair<Int, (String) -> Unit>>> = _getStringEvent

    protected fun handleNavigationAction(action: NavigationAction) {
        _navigationEvent.postValue(SingleEvent(action))
    }

    protected fun hideKeyboard() {
        _hideKeyboardEvent.postValue(SingleEvent(Unit))
    }

    protected fun getString(@StringRes stringResId: Int, callback: (String) -> Unit) {
        _getStringEvent.postValue(SingleEvent(stringResId to callback))
    }
}
