package com.nexters.towhom.ui.write

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context.WINDOW_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.View.DragShadowBuilder
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexters.towhom.MainActivity
import com.nexters.towhom.R
import com.nexters.towhom.core.RxEventBusHelper


class ContentAdapter(private var items: MutableList<String>) :
    RecyclerView.Adapter<ContentAdapter.ContentViewHolder>() {
    lateinit var holder: ContentViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_write_content, parent, false)
        holder = ContentViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setUpdateItems(items: MutableList<String>) {
        this.items = items
    }

    fun uriSendToHolder(param: Uri) {
        holder.setGalleryView(param)
    }

    fun cropUriSendToHolder(param: Bitmap) {
        holder.setGalleryCropView(param)
    }


    inner class ContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var container: ConstraintLayout = itemView.findViewById(R.id.constraint_container)
        private var letterEdit: EditText = itemView.findViewById(R.id.letter_text)
        //private var galleryImage : ConstraintLayout
        private var image: ImageView = itemView.findViewById(R.id.gallery_image)
        private val themeIv: AppCompatImageView = itemView.findViewById(R.id.theme_iv)

        private val GALLERY = 10
        private val CROP_IMGAE = 15

        init {
            //galleryImage = itemView.findViewById(R.id.gallery_image)


            /** edittext 줄 수 제한 **/
            letterEdit.addTextChangedListener(object : TextWatcher {
                var previousstirng = ""
                override fun afterTextChanged(s: Editable?) {
                    if (letterEdit.lineCount >= 11) {
                        Toast.makeText(itemView.context, "편지지를 추가해주세요", Toast.LENGTH_SHORT).show()
                        letterEdit.setText(previousstirng)
                        letterEdit.setSelection(letterEdit.length())
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    previousstirng = s.toString()
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

            })


            /**갤러리 이미지 추가 **/
            image.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                (itemView.context as MainActivity).startActivityForResult(intent, GALLERY)
            }


        }

        fun bind(position: Int) {
            RxEventBusHelper.themeSubject[position].subscribe {
                Log.e("Event_Bus_Call!", it.toString() + " :" + position)
                themeIv.setImageResource(it)
            }

            RxEventBusHelper.fontSubject[position].subscribe {
                Log.e("Event_Bus_Call!", it.toString() + " : " + position)
                letterEdit.typeface = ResourcesCompat.getFont(itemView.context, it.fontResource)
                letterEdit.textSize = it.fontSize.toFloat()
            }


            RxEventBusHelper.fontColorSubject[position].subscribe {
                Log.e("Event_Bus_Call!", it.toString() + " : " + position)
                letterEdit.setTextColor(itemView.context.getColor(it))
                letterEdit.setHintTextColor(itemView.context.getColor(it))
            }


            RxEventBusHelper.stickerSubject[position].subscribe {
                Log.e("Event_Bus_Call!", it.toString() + " : " + position + " = Sticker")


                val stickerImage =
                    LayoutInflater.from(itemView.context)
                        .inflate(R.layout.sticker, container, false)
                Glide.with(itemView.context).load(it)
                    .into(stickerImage.findViewById(R.id.sticker_image))

                stickerImage.setOnTouchListener((View.OnTouchListener { view, motionEvent ->
                    if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                        view.y = motionEvent.rawY - view.height / 2
                        view.x = motionEvent.rawX - view.width / 2
                    }
                    true

                }))


                container.addView(stickerImage)
            }


        }


        fun setGalleryView(param: Uri) {
            var intent: Intent = getCropImageFile(param)
            (itemView.context as MainActivity).startActivityForResult(intent, CROP_IMGAE)
        }

        fun setGalleryCropView(param: Bitmap) {
            image.setImageBitmap(param)
        }

        private fun cropImage(param: Uri) {

        }

        private fun getCropImageFile(imageUri: Uri): Intent {
            val intent = Intent("com.android.camera.action.CROP")
            intent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

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