package com.bonacode.securehome.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.bonacode.securehome.R

class ExpandableControlPanelHeaderView : FrameLayout {
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
        val view = View.inflate(context, R.layout.view_expandable_control_panel_header, null)
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ExpandableControlPanelHeaderView
        )

        view.findViewById<TextView>(R.id.headerTextView).setText(
            typedArray.getResourceId(
                R.styleable.ExpandableControlPanelHeaderView_expandableHeaderTitle,
                0
            )
        )
        view.findViewById<View>(R.id.separator).visibility = if (typedArray.getBoolean(
                R.styleable.ExpandableControlPanelHeaderView_expandableHeaderShowSeparator,
                false
            )
        ) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }

        addView(view)

        typedArray.recycle()
    }
}
