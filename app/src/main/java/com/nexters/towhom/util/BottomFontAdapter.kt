package com.nexters.towhom.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.towhom.R
import com.nexters.towhom.vo.FontVO

class BottomFontAdapter(
    private val parentList: Array<String>,
    private val childList: ArrayList<FontVO>
) :
    RecyclerView.Adapter<BottomFontAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_bottom_font,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = parentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recycler by lazy { itemView.findViewById<RecyclerView>(R.id.content_recycler_view) }


        fun bind(position: Int) {
            recycler.adapter = TabInFontAdapter(childList, position)
        }


    }
}


