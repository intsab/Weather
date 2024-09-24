package com.intsab.dls

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.intsab.dls.databinding.WeekItemBinding

/**
 * Created by intsabhaider
 * on 24,September,2024
 */
class CurrencyItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding: WeekItemBinding by lazy {
        WeekItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.dls_week_item_attr)
        val isGridItem = ta.getBoolean(R.styleable.dls_week_item_attr_isGridItem, false)
        ta.recycle()

        initView()
    }

    private fun initView() {

    }
}