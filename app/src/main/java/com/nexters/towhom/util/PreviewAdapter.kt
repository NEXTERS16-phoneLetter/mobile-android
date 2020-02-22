package com.nexters.towhom.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexters.towhom.R
import com.nexters.towhom.vo.FontVO

class PreviewAdapter(
    private val parentList: Array<String>,
    private val childList: ArrayList<FontVO>
) :
    RecyclerView.Adapter<PreviewAdapter.ViewHolder>() {
    //아이템의 갯수
    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_raw, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindItems(data : User){
            //이미지표시
            Glide.with(itemView.context).load(data.photo)
                .into(itemView.imageView_photo)
            itemView.textView_name.text = data.name
            itemView.textView_email.text = data.email
            //itemView.imageView_photo.setImageBitmap(data.photo)

            //각각의 아이템 클릭시
            itemView.setOnClickListener({
                //여기서 토스터를 어떻게?
                Toast.makeText(itemView.context, "아이템 '${data.name}'를 클릭했습니다.", Toast.LENGTH_LONG).show()
            })
        }



//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        return ViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.item_preview,
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun getItemCount(): Int = parentList.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(position)
//    }
//
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val recycler by lazy { itemView.findViewById<RecyclerView>(R.id.preview_recycler_view) }
//
//
//        fun bind(position: Int) {
//            recycler.adapter = TabInFontAdapter(childList, position)
//        }
//
//
//    }
}


