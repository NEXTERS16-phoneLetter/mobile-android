package com.nexters.towhom.adapter

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition

object GlideBindingAdapter {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: AppCompatImageButton, path: String) {
        Glide.with(view.context).load(path).into(view)
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: AppCompatImageButton, path: Int) {
        Glide.with(view.context).load(path).into(view)
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: AppCompatImageView, path: String) {
        Glide.with(view.context).load(path).into(view)
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setProfileImage(view: AppCompatImageView, path: String) {
        Glide.with(view.context).load(path).circleCrop().into(view)
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: AppCompatImageView, path: Int) {
        Glide.with(view.context).load(path).into(view)
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: ConstraintLayout, path: Int) {
        Glide.with(view.context).load(path).into(object : CustomViewTarget<ConstraintLayout, Drawable>(view) {
            override fun onLoadFailed(errorDrawable: Drawable?) {
                view.background = errorDrawable
            }

            override fun onResourceCleared(placeholder: Drawable?) {
                view.background = placeholder
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                view.background = resource
            }

        })
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(view: LinearLayoutCompat, path: Int) {
        Glide.with(view.context).load(path).into(object : CustomViewTarget<LinearLayoutCompat, Drawable>(view) {
            override fun onLoadFailed(errorDrawable: Drawable?) {
                view.background = errorDrawable
            }

            override fun onResourceCleared(placeholder: Drawable?) {
                view.background = placeholder
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                view.background = resource
            }

        })

    }

}