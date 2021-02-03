package com.bonacode.securehome.architecture.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

sealed class NavigationAction {

    object NavigateBack : NavigationAction()

    data class NavigateTo(@IdRes val id: Int, val extras: Bundle? = null) : NavigationAction()

    data class NavigateBackTo(@IdRes val id: Int, val extras: Bundle? = null) : NavigationAction()
}

class Navigator(
    private val navControllerProvider: () -> NavController
) {
    fun handleNavigationAction(action: NavigationAction) {
        val navController = navControllerProvider.invoke()
        when (action) {
            is NavigationAction.NavigateBack -> navController.popBackStack()
            is NavigationAction.NavigateBackTo -> navController.popBackStack(action.id, false)
            is NavigationAction.NavigateTo -> navController.navigate(action.id, action.extras)
        }
    }
}
