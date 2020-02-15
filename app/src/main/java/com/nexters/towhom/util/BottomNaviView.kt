package com.nexters.towhom.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.nexters.towhom.R


class BottomNaviView : LinearLayout {
    private lateinit var view: View

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView(context)
    }

    private fun initView(context: Context) {
        val infService = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = infService.inflate(R.layout.view_bottom_navi, this, false)



    }


}