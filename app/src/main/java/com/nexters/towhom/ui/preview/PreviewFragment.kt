package com.nexters.towhom.ui.preview

import android.os.Bundle
import android.view.View
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentPreviewBinding

class PreviewFragment : BindingFragment<FragmentPreviewBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_preview

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