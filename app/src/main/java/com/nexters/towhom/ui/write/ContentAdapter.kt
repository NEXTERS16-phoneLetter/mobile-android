package com.nexters.towhom.ui.write

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nexters.towhom.MainActivity
import com.nexters.towhom.R
import java.io.File


class ContentAdapter(private var items: MutableList<String>) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {
    lateinit var holder: ContentViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_write_content, parent, false)
        holder = ContentViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {

    }

    fun setUpdateItems(items: MutableList<String>) {
        this.items = items
    }

    fun uriSendToHolder(param : Uri) {
        holder.setGalleryView(param)
    }
    fun cropUriSendToHolder(param : Bitmap) {
        holder.setGalleryCropView(param)
    }


    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var letterEdit: EditText
        //private var galleryImage : ConstraintLayout
        private var image : ImageView
        private val GALLERY = 10
        private val CROP_IMGAE = 15

        init {
            letterEdit=itemView.findViewById( R.id.letter_text)
            //galleryImage = itemView.findViewById(R.id.gallery_image)
            image = itemView.findViewById(R.id.gallery_image)


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


            /**갤러리 이미지 추가 **/
            image.setOnClickListener{
                val intent = Intent(Intent.ACTION_PICK)
                intent.type="image/*"
                (itemView.context as MainActivity).startActivityForResult(intent, GALLERY)
            }

        }
        fun setGalleryView(param:Uri){
            var intent :Intent = getCropImageFile(param)
            (itemView.context as MainActivity).startActivityForResult(intent, CROP_IMGAE)
        }
        fun setGalleryCropView(param:Bitmap){
            image.setImageBitmap(param)
        }

        private fun cropImage(param: Uri) {

        }

        private fun getCropImageFile(imageUri : Uri): Intent {
            val intent = Intent("com.android.camera.action.CROP")
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            intent.setDataAndType(imageUri, "image/*")
            intent.putExtra("aspectX", 4)
            intent.putExtra("aspectY", 3)
            intent.putExtra("outputX", 400)
            intent.putExtra("outputY", 270)
            intent.putExtra("scale", true)

            //intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri)
            intent.putExtra("return-data", true)

            return intent
        }

    }


}