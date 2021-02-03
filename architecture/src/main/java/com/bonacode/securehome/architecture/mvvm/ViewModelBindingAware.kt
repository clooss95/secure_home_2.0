package com.bonacode.securehome.architecture.mvvm

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.bonacode.securehome.architecture.BR
import com.bonacode.securehome.architecture.navigation.Navigator

interface ViewModelBindingAware<VM : BaseViewModel, B : ViewDataBinding> : LifecycleOwner {
    var binding: B?
    val viewModel: VM

    fun safeGetBinding(): B = binding!!
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.setupLifecycleObservers() {
    lifecycle.addObserver(viewModel)
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.destroyLifecycleObservers() {
    lifecycle.removeObserver(viewModel)
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.setupBinding() {
    binding?.lifecycleOwner = this
    binding?.setVariable(BR.viewModel, viewModel)
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.destroyBinding() {
    binding?.unbind()
    binding?.lifecycleOwner = null
    binding = null
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.observeHideKeyboardEvent(
    hideKeyboardCallback: () -> Unit
) {
    viewModel.hideKeyboardEvent.observe(this, { event ->
        event?.getContentIfNotHandled()?.let {
            hideKeyboardCallback()
        }
    })
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.disposeHideKeyboardEvent() {
    viewModel.hideKeyboardEvent.removeObservers(this)
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.observeNavigationEvent(
    navigator: Navigator
) {
    viewModel.navigationEvent.observe(this, { event ->
        event?.getContentIfNotHandled()?.let { action ->
            navigator.handleNavigationAction(action)
        }
    })
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.observeGetStringEvent(context: Context) {
    viewModel.getStringEvent.observe(this, { event ->
        event?.getContentIfNotHandled()?.let { it ->
            it.second.invoke(context.getString(it.first))
        }
    })
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.disposeNavigationEvent() {
    viewModel.navigationEvent.removeObservers(this)
}

internal fun <VM : BaseViewModel, B : ViewDataBinding> ViewModelBindingAware<VM, B>.disposeGetStringEvent() {
    viewModel.getStringEvent.removeObservers(this)
}
