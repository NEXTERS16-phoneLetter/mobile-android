package com.nexters.towhom.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.navigation.fragment.findNavController
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    override fun getLayoutResId() = R.layout.fragment_home

    private lateinit var letterImage : AppCompatImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = getViewModel()
        letterImage = binding.letterStartImage


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


}
