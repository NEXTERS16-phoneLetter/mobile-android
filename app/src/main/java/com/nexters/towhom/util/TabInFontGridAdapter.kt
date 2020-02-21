package com.nexters.towhom.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.nexters.towhom.R
import com.nexters.towhom.core.RxEventBusHelper
import com.nexters.towhom.vo.FontColorVO
import com.nexters.towhom.vo.FontVO

class TabInFontGridAdapter(
    context: Context,
    private val list: ArrayList<FontVO>,
    private val miniCategory: String
) :
    ArrayAdapter<TabInFontGridAdapter.ViewHolder1>(context, R.layout.item_tab_in_grid_font) {

    val colorList: ArrayList<FontColorVO> = arrayListOf(
        FontColorVO("검정", R.color.colorBlack, R.drawable.box_rad16_black),
        FontColorVO("연검정", R.color.brown_grey, R.drawable.box_rad16_brown_grey),
        FontColorVO("연회", R.color.black_two, R.drawable.box_rad16_black_two),
        FontColorVO("하양", R.color.white, R.drawable.box_rad16_white),
        FontColorVO("자몽", R.color.grapefruit, R.drawable.box_rad16_grape_fruit),
        FontColorVO("망고", R.color.mango, R.drawable.box_rad16_mango),
        FontColorVO("맑은포도", R.color.warm_blue, R.drawable.box_rad16_warm_blue)
    )


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        val holder: ViewHolder1
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tab_in_grid_font, null)
            holder = ViewHolder1().apply {
                tv = view.findViewById(R.id.item_font_grid_tv)
            }
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder1
        }

        if (miniCategory == "fontVO") {
            val fontProperty = list[position]
            val tFace = ResourcesCompat.getFont(context, fontProperty.fontResource)

            holder.tv!!.apply {
                textSize = "16".toFloat()
                typeface = tFace
                text = fontProperty.fontName
                setOnClickListener {
                    RxEventBusHelper.sendFontEvent(list[position])
                }

            }
        } else {

            holder.tv!!.apply {
                typeface = ResourcesCompat.getFont(
                    context,
                    R.font.nanumgothic_extrabold
                )
                text = colorList[position].name
                background = context.getDrawable(colorList[position].colorResource)
                setOnClickListener {
                    RxEventBusHelper.sendFontColorEvent(colorList[position].colorResource)
                }

                if (colorList[position].name == "하양") {
                    setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorBlack
                        )
                    )
                } else {

                    setTextColor(ContextCompat.getColor(context, R.color.white))
                }
            }
        }

        return view!!

    }

    override fun getCount(): Int {
        if (miniCategory == "fontVO") {
            return list.size
        } else {
            return colorList.size
        }
    }


    inner class ViewHolder1 {
        var tv: AppCompatTextView? = null
        val rCornerEffect = context.getDrawable(R.drawable.rounding_corner)
    }
}