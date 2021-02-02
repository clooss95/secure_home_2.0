package com.bonacode.securehome.application.architecture.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bonacode.securehome.application.architecture.navigation.Navigator

abstract class BaseFragment : Fragment() {

    protected val navigator by lazy { Navigator { findNavController() } }

    protected fun hideKeyboard() {
        activity?.currentFocus?.let { v ->
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}
