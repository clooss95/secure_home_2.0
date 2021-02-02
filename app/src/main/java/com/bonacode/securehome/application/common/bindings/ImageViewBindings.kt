package com.bonacode.securehome.application.common.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageViewBindings {

    @BindingAdapter("app:srcCompat")
    @JvmStatic
    fun setSrcCompat(view: ImageView, resId: Int) {
        view.setImageResource(resId)
    }
}
