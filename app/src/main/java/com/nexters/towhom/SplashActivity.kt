package com.nexters.towhom

import android.content.Intent
import android.os.Bundle
import com.nexters.towhom.core.BindingActivity
import com.nexters.towhom.databinding.ActivitySplashBinding
import com.nexters.towhom.login.LoginActivity
import com.nexters.towhom.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BindingActivity<ActivitySplashBinding>() {
    override fun getLayoutResId(): Int = R.layout.activity_splash

    val SPLASH_TIME = 1000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_TIME)
            moveToLoginActivity()
        }


    }

    fun moveToLoginActivity() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }


}