package com.bonacode.securehome.application.common.bindings

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindings {

    @BindingAdapter("android:visibility")
    @JvmStatic
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("app:visibilitySoft")
    @JvmStatic
    fun setVisibilitySoft(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }
}
