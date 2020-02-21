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
import com.nexters.towhom.vo.FontVO


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


    //0 theme, 1 글꼴, 2 스티커
    public fun updateView(category: String) {


        var categoryList: Array<String>? = null

        when (category) {
            "letter" -> {
                categoryList = resources.getStringArray(R.array.letter_str_arr)
                vp.adapter = BottomNavAdapter(categoryList, category)
            }
            "text" -> {
                val fontList: ArrayList<FontVO> = getTestFontList()
                categoryList = resources.getStringArray(R.array.text_str_arr)
                vp.adapter = BottomFontAdapter(categoryList, fontList)
            }
            "sticker" -> {
                categoryList = resources.getStringArray(R.array.sticker_str_arr)
                vp.adapter = BottomNavAdapter(categoryList, category)

            }
        }

        connectTabWithViewPager(categoryList!!, category)
    }


    private fun createTabView(tabName: String): View {
        val v = LayoutInflater.from(context).inflate(R.layout.custom_tab_view, null)
        val tv = v.findViewById<TextView>(R.id.tab_item)
        tv.text = tabName
        return v
    }


    private fun connectTabWithViewPager(tabList: Array<String>, tabName: String) {
        tabs.removeAllTabs()
        repeat(tabList.count()) {
            tabs.addTab(tabs.newTab().setCustomView(createTabView(tabList[it])))
        }

        TabLayoutMediator(tabs, vp) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }


    private fun getTestFontList(): ArrayList<FontVO> {
        val list: ArrayList<FontVO> = arrayListOf()

        list.add(FontVO("16", R.font.bmyeonsung, "베민 연성체"))
        list.add(FontVO("16", R.font.gamjaflower_regular, "감자"))
        list.add(FontVO("16", R.font.himelody_regular, "멜로디체"))
        list.add(FontVO("16", R.font.jua_regular, "주아체"))
        list.add(FontVO("16", R.font.nanumbarunpenregular, "나눔 바름체"))
        list.add(FontVO("16", R.font.nanumgothic_regular, "나눔 고딕체"))
        list.add(FontVO("16", R.font.nanumsquareroundb, "나눔 스퀘어라운드체"))
        list.add(FontVO("16", R.font.notosanskr_medium, "본고딕체"))
        list.add(FontVO("16", R.font.notoserif_regular, "본명체"))
        list.add(FontVO("16", R.font.songmyung_regular, "소명체"))
        list.add(FontVO("16", R.font.thefaceshop_inklipquid, "주야체"))


        return list
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

