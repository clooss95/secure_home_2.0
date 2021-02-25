package com.bonacode.securehome.application.common.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextViewBindings {

    @BindingAdapter("app:textStringRes")
    @JvmStatic
    fun bindTextStringRes(view: TextView, textRes: Int?) {
        if (textRes == null) {
            view.text = ""
        } else {
            view.setText(textRes)
        }
    }
}
