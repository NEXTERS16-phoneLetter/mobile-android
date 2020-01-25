package com.nexters.towhom.core

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @author Kiyeon_Kim (giyeon15@gmail.com)
 * @see [중요] BindingFragment 에서 onViewCreate가 layout을 그려주고 반환해주기 때문에 onViewCreated에 logic 작성할 것
 * */


abstract class BindingFragment<T : ViewDataBinding> : Fragment() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    protected lateinit var binding: T
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<T>(inflater, getLayoutResId(), container, false).apply { binding = this }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingView()

    }

    override fun onStart() {
        super.onStart()
        bindingEventListener()
        bindingObserver()
    }

    abstract fun bindingView()
    abstract fun bindingEventListener()
    abstract fun bindingObserver()

}