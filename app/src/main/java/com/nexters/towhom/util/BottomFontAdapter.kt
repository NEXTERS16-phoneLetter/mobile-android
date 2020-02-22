package com.nexters.towhom.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
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
        val gridView by lazy { itemView.findViewById<GridView>(R.id.content_font_grid_view) }

        fun bind(position: Int) {
            when (position) {
                0 -> {
                    gridView.adapter = TabInFontGridAdapter(itemView.context, childList, "fontVO")

                    showGridHideRecyeler()
                }

                1 -> {
                    recycler.adapter = TabInFontAdapter(childList, position)

                    showRecyclerHideGrid()
                }
                2 -> {
                    gridView.adapter =
                        TabInFontGridAdapter(itemView.context, childList, "fontColorVO")
                    showGridHideRecyeler()
                }
            }
        }

        private fun showGridHideRecyeler() {
            gridView.visibility = View.VISIBLE
            recycler.visibility = View.GONE
        }
        private fun showRecyclerHideGrid() {
            gridView.visibility = View.GONE
            recycler.visibility = View.VISIBLE
        }

    }
}


