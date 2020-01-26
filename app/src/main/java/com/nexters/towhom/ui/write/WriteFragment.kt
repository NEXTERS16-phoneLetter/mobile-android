package com.nexters.towhom.ui.write

import android.os.Bundle
import android.view.View
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentWriteBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WriteFragment : BindingFragment<FragmentWriteBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_write

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = getViewModel ()
    }

    override fun bindingView() {
    }

    override fun bindingEventListener() {
    }

    override fun bindingObserver() {
    }

}