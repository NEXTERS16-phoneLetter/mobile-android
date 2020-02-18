package com.nexters.towhom.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.findNavController
import com.nexters.towhom.MainActivity
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingActivity
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(), BindingActivity.OnBackPressedListener {
    override fun getLayoutResId() = R.layout.fragment_home

    private var BACKPRESSED_TIME: Long = 0
    private val FINISH_INTERVAL_TIME: Int = 2000


    private lateinit var letterImage : AppCompatImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = getViewModel()
        letterImage = binding.letterStartImage


        (activity as MainActivity).setOnKeyBackPressedListener(this) //backPressed 처리
        //binding.userName.text = "From. " + CommonData.USER_NAME
        //GlideBindingAdapter.setProfileImage(binding.userImage, CommonData.USER_IMAGE_URL)



    }

    override fun bindingView() {
       // writeBtn = binding.writeContainer
    }


    override fun bindingEventListener() {

        letterImage.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE){
                v.x = event.rawX - v.width / 2

            }
            if (event.action == MotionEvent.ACTION_UP) {
                if(v.x < 600){
                    findNavController().navigate(R.id.action_home_to_write)
                }
            }
            true

        }




    }

    override fun bindingObserver() {
    }

    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - BACKPRESSED_TIME

        if (intervalTime in 0..FINISH_INTERVAL_TIME) {
            activity!!.finish()
        } else {
            BACKPRESSED_TIME = tempTime
            Toast.makeText(context, resources.getString(R.string.sys_backpress_msg), Toast.LENGTH_SHORT).show()
        }
    }


}
