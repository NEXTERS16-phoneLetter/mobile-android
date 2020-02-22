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
import com.nexters.towhom.vo.*


class BottomNaviView : RelativeLayout {
    private lateinit var view: View
    private var STATUS = -1 // -1 default
    // 0 테마 1 글꼴 2 텍스

    private val tabs by lazy {
        view.findViewById<TabLayout>(R.id.tab)
    }
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
                val themeList = getTestThemeList()
                categoryList = resources.getStringArray(R.array.letter_str_arr)
                vp.adapter = BottomNavThemeAdapter(categoryList, themeList)

//                vp.adapter = BottomNavAdapter(categoryList, , category)
            }
            "text" -> {
                val fontList: ArrayList<FontVO> = getTestFontList()
                categoryList = resources.getStringArray(R.array.text_str_arr)
                vp.adapter = BottomFontAdapter(categoryList, fontList)
            }
            "sticker" -> {
                val stickerList: ArrayList<StickerThemeVO> = getTestStickerList()
                categoryList = resources.getStringArray(R.array.sticker_str_arr)
                vp.adapter = BottomNavAdapter(categoryList, stickerList, category)

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

    private fun getTestStickerList(): ArrayList<StickerThemeVO> {
        val list: ArrayList<StickerThemeVO> = arrayListOf()
        list.add(StickerThemeVO("emoji", getTestEachList("emoji")))
        list.add(StickerThemeVO("heart", getTestEachList("heart")))
        list.add(StickerThemeVO("nugu", getTestEachList("nugu")))
        list.add(StickerThemeVO("wheather", getTestEachList("wheather")))

        return list
    }

    private fun getTestThemeList(): ArrayList<ThemeCategoryVO> {
        val list: ArrayList<ThemeCategoryVO> = arrayListOf()

        list.add(ThemeCategoryVO("brush", getTestEachThemeList("brush")))
        list.add(ThemeCategoryVO("paper", getTestEachThemeList("paper")))
        return list
    }

    private fun getTestEachThemeList(theme: String): ArrayList<ThemeVO> {
        val list: ArrayList<ThemeVO> = arrayListOf()
        when (theme) {
            "brush" -> {
                list.add(ThemeVO("brush1", R.drawable.brush_1))
                list.add(ThemeVO("brush2", R.drawable.brush_2))
                list.add(ThemeVO("brush3", R.drawable.brush_3))
                list.add(ThemeVO("brush4", R.drawable.brush_4))
                list.add(ThemeVO("brush5", R.drawable.brush_5))
                list.add(ThemeVO("brush6", R.drawable.brush_6))
                list.add(ThemeVO("brush7", R.drawable.brush_7))
                list.add(ThemeVO("brush8", R.drawable.brush_8))
                list.add(ThemeVO("brush9", R.drawable.brush_9))
                list.add(ThemeVO("brush10", R.drawable.brush_10))
                list.add(ThemeVO("brush11", R.drawable.brush_11))
                list.add(ThemeVO("brush12", R.drawable.brush_12))
            }
            "paper" -> {
                list.add(ThemeVO("paper1", R.drawable.paper_1))
                list.add(ThemeVO("paper2", R.drawable.paper_2))
                list.add(ThemeVO("paper3", R.drawable.paper_3))
                list.add(ThemeVO("paper4", R.drawable.paper_4))
                list.add(ThemeVO("paper5", R.drawable.paper_5))
                list.add(ThemeVO("paper6", R.drawable.paper_6))
                list.add(ThemeVO("paper7", R.drawable.paper_7))
                list.add(ThemeVO("paper8", R.drawable.paper_8))
                list.add(ThemeVO("paper9", R.drawable.paper_9))
                list.add(ThemeVO("paper10", R.drawable.paper_10))
                list.add(ThemeVO("paper11", R.drawable.paper_11))
                list.add(ThemeVO("paper12", R.drawable.paper_12))
            }
        }
        return list
    }

    private fun getTestEachList(theme: String): ArrayList<StickerVO> {
        val list: ArrayList<StickerVO> = arrayListOf()
        when (theme) {
            "emoji" -> {
                list.add(StickerVO("emoji1", R.drawable.ic_emoji_1))
                list.add(StickerVO("emoji2", R.drawable.ic_emoji_2))
                list.add(StickerVO("emoji3", R.drawable.ic_emoji_3))
                list.add(StickerVO("emoji4", R.drawable.ic_emoji_4))
                list.add(StickerVO("emoji5", R.drawable.ic_emoji_5))
                list.add(StickerVO("emoji6", R.drawable.ic_emoji_6))
                list.add(StickerVO("emoji7", R.drawable.ic_emoji_7))
                list.add(StickerVO("emoji8", R.drawable.ic_emoji_8))
                list.add(StickerVO("emoji9", R.drawable.ic_emoji_9))
                list.add(StickerVO("emoji10", R.drawable.ic_emoji_10))
                list.add(StickerVO("emoji11", R.drawable.ic_emoji_11))
                list.add(StickerVO("emoji12", R.drawable.ic_emoji_12))
                list.add(StickerVO("emoji13", R.drawable.ic_emoji_13))
                list.add(StickerVO("emoji14", R.drawable.ic_emoji_14))
                list.add(StickerVO("emoji15", R.drawable.ic_emoji_15))
                list.add(StickerVO("emoji16", R.drawable.ic_emoji_16))
                list.add(StickerVO("emoji17", R.drawable.ic_emoji_17))
                list.add(StickerVO("emoji18", R.drawable.ic_emoji_18))
                list.add(StickerVO("emoji19", R.drawable.ic_emoji_19))
                list.add(StickerVO("emoji20", R.drawable.ic_emoji_20))
                list.add(StickerVO("emoji21", R.drawable.ic_emoji_21))
                list.add(StickerVO("emoji22", R.drawable.ic_emoji_22))
                list.add(StickerVO("emoji23", R.drawable.ic_emoji_23))
                list.add(StickerVO("emoji24", R.drawable.ic_emoji_24))
                list.add(StickerVO("emoji25", R.drawable.ic_emoji_25))
                list.add(StickerVO("emoji26", R.drawable.ic_emoji_26))
                list.add(StickerVO("emoji27", R.drawable.ic_emoji_27))
                list.add(StickerVO("emoji28", R.drawable.ic_emoji_28))
                list.add(StickerVO("emoji29", R.drawable.ic_emoji_29))
                list.add(StickerVO("emoji30", R.drawable.ic_emoji_30))
            }
            "heart" -> {
                list.add(StickerVO("heart1", R.drawable.ic_heart_1))
                list.add(StickerVO("heart2", R.drawable.ic_heart_2))
                list.add(StickerVO("heart3", R.drawable.ic_heart_3))
                list.add(StickerVO("heart4", R.drawable.ic_heart_4))
                list.add(StickerVO("heart5", R.drawable.ic_heart_5))
                list.add(StickerVO("heart6", R.drawable.ic_heart_6))
                list.add(StickerVO("heart7", R.drawable.ic_heart_7))
                list.add(StickerVO("heart8", R.drawable.ic_heart_8))
                list.add(StickerVO("heart9", R.drawable.ic_heart_9))
                list.add(StickerVO("heart10", R.drawable.ic_heart_10))
                list.add(StickerVO("heart11", R.drawable.ic_heart_11))
                list.add(StickerVO("heart12", R.drawable.ic_heart_12))
            }
            "nugu" -> {
                list.add(StickerVO("nugu1", R.drawable.ic_nugu_1))
                list.add(StickerVO("nugu2", R.drawable.ic_nugu_2))
                list.add(StickerVO("nugu3", R.drawable.ic_nugu_3))
                list.add(StickerVO("nugu4", R.drawable.ic_nugu_4))
                list.add(StickerVO("nugu5", R.drawable.ic_nugu_5))
                list.add(StickerVO("nugu6", R.drawable.ic_nugu_6))
                list.add(StickerVO("nugu7", R.drawable.ic_nugu_7))
                list.add(StickerVO("nugu8", R.drawable.ic_nugu_8))
                list.add(StickerVO("nugu9", R.drawable.ic_nugu_9))
            }
            "wheather" -> {
                list.add(StickerVO("wheather1", R.drawable.ic_weather_1))
                list.add(StickerVO("wheather2", R.drawable.ic_weather_2))
                list.add(StickerVO("wheather3", R.drawable.ic_weather_3))
                list.add(StickerVO("wheather4", R.drawable.ic_weather_4))
                list.add(StickerVO("wheather5", R.drawable.ic_weather_5))
                list.add(StickerVO("wheather6", R.drawable.ic_weather_6))
                list.add(StickerVO("wheather7", R.drawable.ic_weather_7))
                list.add(StickerVO("wheather8", R.drawable.ic_weather_8))
                list.add(StickerVO("wheather9", R.drawable.ic_weather_9))
            }
        }
        return list
    }


    private fun getTestFontList(): ArrayList<FontVO> {
        val list: ArrayList<FontVO> = arrayListOf()

        list.add(FontVO("24", R.font.bmyeonsung, "연성"))
        list.add(FontVO("24", R.font.gamjaflower_regular, "감자"))
        list.add(FontVO("24", R.font.himelody_regular, "멜로디"))
        list.add(FontVO("24", R.font.jua_regular, "주아"))
        list.add(FontVO("24", R.font.nanumbarunpenregular, "나눔 바름"))
        list.add(FontVO("21", R.font.nanumgothic_regular, "나눔 고딕"))
        list.add(FontVO("21", R.font.nanumsquareroundb, "나눔스퀘어라운드"))
        list.add(FontVO("21", R.font.notosanskr_medium, "본고딕"))
        list.add(FontVO("21", R.font.notoserif_regular, "본명조"))
        list.add(FontVO("23", R.font.songmyung_regular, "송명조"))
        list.add(FontVO("24", R.font.thefaceshop_inklipquid, "더 페이스샵"))


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

