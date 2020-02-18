package com.nexters.towhom.ui.write

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nexters.towhom.MainActivity
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
        private lateinit var letterEdit: EditText
        private lateinit var galleryImage : ImageView
        private val GALLERY = 0
        init {
            letterEdit=itemView.findViewById<EditText>( R.id.letter_text)
            galleryImage = itemView.findViewById(R.id.gallery_image)

            /** edittext 줄 수 제한 **/
            letterEdit.addTextChangedListener(object:TextWatcher{
                var previousstirng = ""
                override fun afterTextChanged(s: Editable?) {
                    if(letterEdit.lineCount >= 11){
                        Toast.makeText(itemView.context, "편지지를 추가해주세요", Toast.LENGTH_SHORT).show()
                        letterEdit.setText(previousstirng)
                        letterEdit.setSelection(letterEdit.length())
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    previousstirng = s.toString()
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

            })

            galleryImage.setOnClickListener{
                val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type="image/*"
                (itemView.context as MainActivity).startActivityForResult(intent, GALLERY)

            }

        }
        /**
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if(requestCode == GALLERY && resultCode == RESULT_OK ){
                val selectedImageUri: Uri ? = data?.data
                =
                galleryImage.setImageURI(selectedImageUri)
            }
        }

        **/

    }

}