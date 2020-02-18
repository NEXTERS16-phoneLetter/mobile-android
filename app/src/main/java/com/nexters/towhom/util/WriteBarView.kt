package com.nexters.towhom.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.nexters.towhom.R

class WriteBarView : RelativeLayout {
    private lateinit var view: View

    private val tabs by lazy { view.findViewById<TabLayout>(R.id.tab) }
    private val vp by lazy { view.findViewById<ViewPager2>(R.id.content_vp) }

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
        view = infService.inflate(R.layout.view_write_bar, this)

        /*  val text = view.findViewById<LinearLayout>(R.id.top_linear)
          text.setOnClickListener {
              Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show()
          }
        */

        val btn_back = view.findViewById<ConstraintLayout>(R.id.btn_write_back)
        btn_back.setOnClickListener {
//            onBackPressed();
            // 뒤로가기
        }

        val btn_success = view.findViewById<ConstraintLayout>(R.id.btn_write_success)
        btn_success.setOnClickListener{
            // 편지쓰기 완료
       }
    }


}
