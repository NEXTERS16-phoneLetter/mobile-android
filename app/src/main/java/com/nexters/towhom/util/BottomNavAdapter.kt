package com.nexters.towhom.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.towhom.R

class BottomNavAdapter(private val list: Array<String>) :
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
        val testTextView by lazy { itemView.findViewById<AppCompatTextView>(R.id.item_view_1) }

        fun bind(position: Int) {
            testTextView.text = list[position]
        }
    }

}