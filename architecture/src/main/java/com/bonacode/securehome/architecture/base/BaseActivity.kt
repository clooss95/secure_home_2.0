package com.bonacode.securehome.architecture.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.bonacode.securehome.architecture.navigation.Navigator

abstract class BaseActivity(@IdRes private val navControllerResId: Int) : AppCompatActivity() {

    val navController: NavController by lazy { findNavController(navControllerResId) }

    protected val navigator by lazy { Navigator { navController } }

    protected fun hideKeyboard() {
        currentFocus?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}
