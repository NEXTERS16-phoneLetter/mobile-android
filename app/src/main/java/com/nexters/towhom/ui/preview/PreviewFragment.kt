package com.nexters.towhom.ui.preview

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentPreviewBinding
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.view_write_bar.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.Observer

class PreviewFragment : BindingFragment<FragmentPreviewBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_preview

    private val vm by lazy { binding.viewModel!! }
//    private val writeSuccessBtn by lazy { binding. }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.viewModel = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun bindingView() {

    }

    override fun bindingEventListener() {
//        btn_write_success.setOnClickListener {
//            findNavController().navigate(R.id.action_signup_to_phone)
//        }
    }

    override fun bindingObserver() {

    }

}