package com.bonacode.securehome.application.common.bindings

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.bonacode.securehome.ui.feature.main.activity.callback.PinCodeEnteredCallback

object EditTextBindings {

    @BindingAdapter(
        value = ["app:pinCodeEnteredCallback", "app:pinCodeEnteredCallbackEnabled"],
        requireAll = true
    )
    @JvmStatic
    fun setEnterPinViewDoneCallback(
        view: EditText,
        callback: PinCodeEnteredCallback,
        buttonEnabled: Boolean
    ) {
        view.setOnEditorActionListener { _, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                if (buttonEnabled) {
                    callback.pinCodeEntered()
                }
            }
            false
        }
    }
}
