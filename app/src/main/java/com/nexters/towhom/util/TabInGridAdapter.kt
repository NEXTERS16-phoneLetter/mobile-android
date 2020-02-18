package com.nexters.towhom.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nexters.towhom.R

class TabInGridAdapter(val context: Context, private val list: List<String>) :
    BaseAdapter() {
    var inf: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = inf.inflate(R.layout.item_tab_in_grid, null)
//        foodView.imgFood.setImageResource(food.image!!)
//        foodView.tvName.text = food.name!!
        return v
    }

    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(p0: Int): Long = p0 as Long

    override fun getCount(): Int = list.size
}