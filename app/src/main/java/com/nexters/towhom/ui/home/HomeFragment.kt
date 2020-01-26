package com.nexters.towhom.ui.home

import android.os.Bundle
import android.view.View
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentHomeBinding
import com.nexters.towhom.adapter.GlideBindingAdapter
import com.nexters.towhom.vo.CommonData
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    override fun getLayoutResId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = getViewModel()

        binding.userName.text = "From. " + CommonData.USER_NAME
        GlideBindingAdapter.setProfileImage(binding.userImage, CommonData.USER_IMAGE_URL)

    }

    override fun bindingView() {
    }

    override fun bindingEventListener() {
    }

    override fun bindingObserver() {
    }


}
