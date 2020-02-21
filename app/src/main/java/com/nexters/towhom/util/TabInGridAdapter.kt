package com.nexters.towhom.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import com.nexters.towhom.R

class TabInGridAdapter(context: Context, private val list: List<String>) :
    ArrayAdapter<TabInGridAdapter.ViewHolder1>(context, R.layout.item_tab_in_grid) {


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
            setImageResource(R.drawable.kakao_default_profile_image)
            background = holder.rCornerEffect
            clipToOutline = true
        }

        return view!!

    }

    override fun getCount(): Int = list.size


    inner class ViewHolder1 {
        var iv: AppCompatImageView? = null
        val rCornerEffect = context.getDrawable(R.drawable.rounding_corner)
    }
}