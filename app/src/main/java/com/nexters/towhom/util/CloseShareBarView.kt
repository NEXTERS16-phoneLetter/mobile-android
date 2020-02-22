package com.nexters.towhom.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.nexters.towhom.R
import com.nexters.towhom.ui.write.WriteFragment

class CloseShareBarView : RelativeLayout {
    private lateinit var view: View

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView(context)
    }

    private fun initView(context: Context) {
        val infService = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = infService.inflate(R.layout.view_close_share, this)

        val btn_tab_close = view.findViewById<AppCompatImageButton>(R.id.btn_tab_close)
        btn_tab_close.setOnClickListener {
//            onBackPressed();
            // 종료하기

        }

        val btn_tab_share = view.findViewById<AppCompatImageButton>(R.id.btn_tab_share)
        btn_tab_share.setOnClickListener {
            // 공유하기

        }

    }


}
