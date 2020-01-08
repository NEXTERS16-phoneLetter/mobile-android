package com.nexters.towhom.mainactivity

import android.os.Bundle
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingActivity
import com.nexters.towhom.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>() {
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}
