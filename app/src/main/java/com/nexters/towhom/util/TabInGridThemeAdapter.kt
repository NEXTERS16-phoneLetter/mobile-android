package com.nexters.towhom.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.nexters.towhom.R
import com.nexters.towhom.core.RxEventBusHelper
import com.nexters.towhom.vo.ThemeVO

class TabInGridThemeAdapter(context: Context, private val list: ArrayList<ThemeVO>) :
    ArrayAdapter<TabInGridThemeAdapter.ViewHolder1>(context, R.layout.item_tab_in_grid) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        val holder: ViewHolder1
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_tab_in_grid, null)
            holder = ViewHolder1().apply {
                iv = view.findViewById(R.id.tab_item_iv)
            }
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder1
        }



        holder.iv!!.apply {
            Glide.with(context).load(list[position].resource).into(this)
            background = holder.rCornerEffect
            setPadding(0, 0, 0, 0)
            clipToOutline = true
            scaleType = ImageView.ScaleType.CENTER_CROP


            setOnClickListener {
                RxEventBusHelper.sendThemeEvent(list[position].resource)
            }
        }

        return view!!

    }

    override fun getCount(): Int = list.size


    inner class ViewHolder1 {
        var iv: AppCompatImageView? = null
        val rCornerEffect = context.getDrawable(R.drawable.box_rad16_white94)
    }
}