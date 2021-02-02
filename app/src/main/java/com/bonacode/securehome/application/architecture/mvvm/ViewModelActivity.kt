package com.bonacode.securehome.application.architecture.mvvm

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bonacode.securehome.application.architecture.base.BaseActivity

abstract class ViewModelActivity<VM : BaseViewModel, B : ViewDataBinding>(
    @LayoutRes private val resId: Int,
    @IdRes private val navControllerResId: Int
) : BaseActivity(navControllerResId), ViewModelBindingAware<VM, B> {

    override var binding: B? = null

    open fun subscribe() {}

    open fun unsubscribe() {}

    open fun initViews() {}

    private fun onCreateViewDataBinding(): B = DataBindingUtil.setContentView(this, resId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = onCreateViewDataBinding()
        setupBinding()
        setupLifecycleObservers()
        observeNavigationEvent(navigator)
        observeHideKeyboardEvent { hideKeyboard() }
        observeGetStringEvent(this)
        initViews()
        subscribe()
    }

    override fun onDestroy() {
        disposeNavigationEvent()
        destroyLifecycleObservers()
        destroyBinding()
        disposeHideKeyboardEvent()
        disposeGetStringEvent()
        unsubscribe()
        super.onDestroy()
    }
}
