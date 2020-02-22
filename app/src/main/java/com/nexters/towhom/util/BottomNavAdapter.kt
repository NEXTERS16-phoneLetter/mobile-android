package com.nexters.towhom.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.nexters.towhom.R
import com.nexters.towhom.vo.StickerThemeVO
import com.nexters.towhom.vo.StickerVO

class BottomNavAdapter(
    private val parentList: Array<String>,
    private val childList: ArrayList<StickerThemeVO>,
    private val tabName: String
) :
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

    override fun getItemCount(): Int = parentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val grid by lazy { itemView.findViewById<GridView>(R.id.content_grid_view) }


        fun bind(position: Int) {
            val stickerVOList: ArrayList<StickerVO> = childList[position].list

            grid.adapter = TabInGridAdapter(itemView.context, stickerVOList) //ArrayList<StickerVO>

        }
    }

    inner class FontViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}