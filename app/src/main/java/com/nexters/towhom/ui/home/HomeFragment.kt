package com.nexters.towhom.ui.home

import android.os.Bundle
import android.view.View
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentHomeBinding

class HomeFragment : BindingFragment<FragmentHomeBinding>() {
    override fun getLayoutResId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun bindingView() {
    }

    override fun bindingEventListener() {
    }

    override fun bindingObserver() {
    }


}
