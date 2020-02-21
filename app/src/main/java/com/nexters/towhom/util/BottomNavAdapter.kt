package com.nexters.towhom.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.towhom.R

class BottomNavAdapter(private val list: Array<String>, private val tabName: String) :
    RecyclerView.Adapter<BottomNavAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bottom_navi,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val grid by lazy { itemView.findViewById<GridView>(R.id.content_grid_view) }


        fun bind(position: Int) {
            when (tabName) {
                "letter" -> {

                }
                "text" -> {

                }
                "sticker" -> {

                }
            }
            val testList = arrayListOf<String>("aa","bb","aa","bb","aa","bb","aa","bb","aa","bb","aa","bb","aa","bb","aa","bb")



            grid.adapter = TabInGridAdapter(itemView.context, testList)

        }
    }

    inner class FontViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}