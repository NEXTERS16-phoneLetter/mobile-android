package com.nexters.towhom.ui.signup

import android.os.Bundle
import android.view.View
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentSignupBinding

class SignupFragment : BindingFragment<FragmentSignupBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_signup



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
    }

    override fun bindingView() {

    }

    override fun bindingEventListener() {

    }

    override fun bindingObserver() {
    }


}