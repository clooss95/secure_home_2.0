package com.bonacode.securehome.architecture.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.bonacode.securehome.architecture.base.BaseFragment

abstract class ViewModelFragment<VM : BaseViewModel, B : ViewDataBinding> : BaseFragment(),
    ViewModelBindingAware<VM, B> {

    override var binding: B? = null

    open fun subscribe() {}

    open fun unsubscribe() {}

    open fun initViews() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLifecycleObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = onCreateViewDataBinding(inflater, container, savedInstanceState).apply {
        binding = this
    }.root

    abstract fun onCreateViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): B

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding()
        observeNavigationEvent(navigator)
        observeHideKeyboardEvent { hideKeyboard() }
        observeGetStringEvent(requireContext())
        initViews()
        subscribe()
    }

    override fun onDestroy() {
        destroyLifecycleObservers()
        super.onDestroy()
    }

    override fun onDestroyView() {
        unsubscribe()
        disposeNavigationEvent()
        destroyBinding()
        disposeHideKeyboardEvent()
        disposeGetStringEvent()
        super.onDestroyView()
    }
}
