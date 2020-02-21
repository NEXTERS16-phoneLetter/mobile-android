package com.nexters.towhom.util

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.Resource
import com.nexters.towhom.R
import com.nexters.towhom.core.RxEventBusHelper
import com.nexters.towhom.vo.FontColorVO
import com.nexters.towhom.vo.FontVO
import com.nexters.towhom.vo.FontWeightVO

class TabInFontAdapter(private val list: ArrayList<FontVO>, private val miniCategory: Int) :
    RecyclerView.Adapter<TabInFontAdapter.ViewHolder>() {

    /** miniCategory
     * 0: 글꼴, 1: 굵기, 2: 색상
     * */

    val weightList: ArrayList<FontWeightVO> = arrayListOf(
        FontWeightVO("얇게", "100"),
        FontWeightVO("보통", "400"),
        FontWeightVO("굵게", "800")
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_tab_in_font,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        when (miniCategory) {
            0 -> return list.size
            1 -> return 3
//            2 -> return colorList.size
        }

        return -1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv by lazy { itemView.findViewById<AppCompatTextView>(R.id.item_font_tv) }

        init {


        }

        fun bind(position: Int) {
            val fontProperty: FontVO = list[position]
            val tFace = ResourcesCompat.getFont(itemView.context, fontProperty.fontResource)

            when (miniCategory) {
                0 -> {
                    tv.apply {
                        textSize = fontProperty.fontSize.toFloat()
                        typeface = tFace
                        text = fontProperty.fontName

                    }
                }
                //굵
                1 -> {
                    tv.apply {
                        text = weightList[position].text
                        typeface =
                            when (position) {
                                0 -> ResourcesCompat.getFont(
                                    itemView.context,
                                    R.font.nanumbarunpenregular
                                )
                                1 -> ResourcesCompat.getFont(
                                    itemView.context,
                                    R.font.nanumgothic_bold
                                )
                                2 -> ResourcesCompat.getFont(
                                    itemView.context,
                                    R.font.nanumgothic_extrabold
                                )
                                else -> null
                            }

                    }
                }

                //색상
                2 -> {
                    /*  tv.apply {
                          typeface = ResourcesCompat.getFont(
                              itemView.context,
                              R.font.nanumgothic_extrabold
                          )
                          text = colorList[position].name
                          background = itemView.context.getDrawable(colorList[position].colorBg)

                          if (colorList[position].name == "하양") {
                              setTextColor(
                                  ContextCompat.getColor(
                                      itemView.context,
                                      R.color.colorBlack
                                  )
                              )
                          } else {

                              setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                          }
                      }
                          */
                }

            }

        }
    }
}
