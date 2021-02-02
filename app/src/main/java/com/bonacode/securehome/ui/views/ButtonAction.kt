package com.bonacode.securehome.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bonacode.securehome.R

class ButtonAction : FrameLayout {
    constructor(context: Context) : super(context) {
        initView(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        val view = View.inflate(context, R.layout.view_button_action, null)

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ButtonAction
        )

        view.findViewById<ImageView>(R.id.icon).setImageResource(
            typedArray.getResourceId(
                R.styleable.ButtonAction_buttonActionIcon,
                0
            )
        )
        view.findViewById<TextView>(R.id.label)
            .setText(typedArray.getResourceId(R.styleable.ButtonAction_buttonActionLabel, 0))

        typedArray.recycle()

        addView(view)

        isClickable = true
        isFocusable = true
    }
}
