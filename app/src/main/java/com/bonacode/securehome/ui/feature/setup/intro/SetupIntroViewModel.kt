package com.bonacode.securehome.ui.feature.setup.intro

import androidx.hilt.lifecycle.ViewModelInject
import com.bonacode.securehome.R
import com.bonacode.securehome.architecture.mvvm.BaseViewModel
import com.bonacode.securehome.architecture.navigation.NavigationAction

class SetupIntroViewModel @ViewModelInject constructor() : BaseViewModel() {

    fun navigateForward() {
        handleNavigationAction(
            NavigationAction.NavigateTo(
                R.id.action_setupIntroFragment_to_setupEnterPhoneNumberFragment
            )
        )
    }
}
