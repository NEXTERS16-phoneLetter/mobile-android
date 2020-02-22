package com.nexters.towhom

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.nexters.towhom.core.BindingActivity
import com.nexters.towhom.databinding.ActivityMainBinding
import com.nexters.towhom.ui.write.WriteFragment


class MainActivity : BindingActivity<ActivityMainBinding>() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }


    private fun updateStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = this@MainActivity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == RESULT_OK && data?.data != null ){
            val selectedImageUri: Uri? = data?.data
            val fragmentManager = supportFragmentManager.findFragmentById(R.id.login_nav_host_fragment)
            val getFirstStackFragment = fragmentManager!!.childFragmentManager.fragments[0]
            (getFirstStackFragment as WriteFragment).GalleryPaste(selectedImageUri!!)
        }
        if(requestCode == 15){
            val extras: Bundle? = data?.extras
            val imageBitmap = extras!!.get("data") as Bitmap
            val fragmentManager = supportFragmentManager.findFragmentById(R.id.login_nav_host_fragment)
            val getFirstStackFragment = fragmentManager!!.childFragmentManager.fragments[0]
            (getFirstStackFragment as WriteFragment).GalleryCropPaste(imageBitmap)
        }
    }



}