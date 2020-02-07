package com.nexters.towhom.ui.write

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.towhom.R

class ContentAdapter(private var items: MutableList<String>) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_write_content, parent, false)
        return ContentViewHolder(view)


    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {

    }

    fun setUpdateItems(items: MutableList<String>) {
        this.items = items
    }


    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

        }

    }
}