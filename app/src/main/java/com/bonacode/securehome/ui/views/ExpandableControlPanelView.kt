package com.bonacode.securehome.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.children
import com.bonacode.securehome.R
import net.cachapa.expandablelayout.ExpandableLayout

class ExpandableControlPanelView : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var header: ExpandableControlPanelHeaderView? = null
    private var body: ExpandableLayout? = null

    init {
        initView()
    }

    var headerClickCallback: (() -> Unit)? = null

    fun collapse() {
        body?.collapse()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)

        header = children.find { it is ExpandableControlPanelHeaderView } as? ExpandableControlPanelHeaderView
        body = children.find { it is ExpandableLayout } as? ExpandableLayout

        header?.setOnClickListener {
            body?.toggle()
            headerClickCallback?.invoke()
        }

        body?.isExpanded?.let { isExpanded ->
            header?.findViewById<ImageView>(R.id.arrow)?.let { arrow ->
                if (isExpanded) {
                    arrow.rotation = 180.0f
                } else {
                    arrow.rotation = 0.0f
                }
            }
        }
    }

    private fun initView() {
        orientation = VERTICAL
    }
}
