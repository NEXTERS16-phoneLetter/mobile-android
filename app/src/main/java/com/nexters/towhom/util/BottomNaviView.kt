package com.nexters.towhom.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nexters.towhom.R


class BottomNaviView : RelativeLayout {
    private lateinit var view: View
    private var STATUS = -1 // -1 default
    // 0 테마 1 글꼴 2 텍스

    private val tabs by lazy { view.findViewById<TabLayout>(R.id.tab) }
    private val vp by lazy { view.findViewById<ViewPager2>(R.id.content_vp) }

//    private val testly by lazy { view.findViewById<LinearLayout>(R.id.top_linear) }

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
        view = infService.inflate(R.layout.view_bottom_navi, this)

        /*  val text = view.findViewById<LinearLayout>(R.id.top_linear)
          text.setOnClickListener {
              Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show()
          }
  */
    }


    //0 theme, 1 글꼴, 2 스티
    public fun updateView(category: String) {
        when (category) {
            "letter" -> showThemeView()
            "text" -> showFontView()
            "sticker" -> showStickerView()
        }
    }

    private fun showThemeView() {
        tabs.removeAllTabs()
        val tabList = resources.getStringArray(R.array.letter_str_arr)

        repeat(tabList.count()) {
            tabs.addTab(tabs.newTab().setCustomView(createTabView(tabList[it])))
        }

        vp.adapter = BottomNavAdapter(tabList)

    /*    vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                vp.currentItem = position
            }
        })

        TabLayoutMediator(tabs, vp) { tab, position ->

        }.attach()*/


    }

    private fun createTabView(tabName: String): View {
        val v = LayoutInflater.from(context).inflate(R.layout.custom_tab_view, null)
        val tv = v.findViewById<TextView>(R.id.tab_item)
        tv.text = tabName
        return v
    }


    private fun showFontView() {
        tabs.removeAllTabs()
        resources.getStringArray(R.array.text_str_arr).apply {
            repeat(this.count()) {
                tabs.addTab(tabs.newTab().setCustomView(createTabView(this[it])))
            }
        }
    }

    private fun showStickerView() {
        tabs.removeAllTabs()
        resources.getStringArray(R.array.sticker_str_arr).apply {
            repeat(this.count()) {
                tabs.addTab(tabs.newTab().setCustomView(createTabView(this[it])))
            }
        }




    }


}

/*  private fun isShowCategory(size: Int) {
      repeat(categoryList.size) { i ->
          if (i >= size) {
              categoryList[i].visibility = View.GONE
          } else {
              categoryList[i].text = themeTitleList[i]
          }
      }

  }
*/

