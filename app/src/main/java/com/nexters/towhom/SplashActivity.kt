package com.nexters.towhom

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.nexters.towhom.core.BindingActivity
import com.nexters.towhom.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class SplashActivity : BindingActivity<ActivitySplashBinding>() {
    override fun getLayoutResId(): Int = R.layout.activity_splash

    val SPLASH_TIME = 1000L
    val TAG = javaClass.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getHashKey()

        CoroutineScope(Dispatchers.Main).launch {
            delay(SPLASH_TIME)
            moveToLoginMainActivity()
        }


    }

    fun moveToLoginMainActivity() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()

    }

    private fun getHashKey() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES).apply {
                    signingInfo.signingCertificateHistory.forEach {
                        getSignature(it)
                    }
                }
            } else {
                packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).apply {
                    signatures.forEach {
                        getSignature(it)
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    private fun getSignature(signature: Signature) {
        val md = MessageDigest.getInstance("SHA")
        md.update(signature.toByteArray())
        Log.d(TAG, "key_hash=${Base64.encodeToString(md.digest(), Base64.DEFAULT)}")
    }


}