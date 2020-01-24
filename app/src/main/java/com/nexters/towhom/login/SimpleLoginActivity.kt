package com.nexters.towhom.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingActivity
import com.nexters.towhom.databinding.ActivitySimpleLoginBinding
import com.nexters.towhom.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SimpleLoginActivity : BindingActivity<ActivitySimpleLoginBinding>() {
    override fun getLayoutResId(): Int = R.layout.activity_simple_login

    private val vm by lazy { binding.viewModel!! }
    private val kakaoLoginBtn by lazy { binding.kakaoLoginBtn }
    private val appLoginBtn by lazy { binding.appLoginBtn }
    private val TAG = SimpleLoginActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = getViewModel()

        val kakaoCallback = SessionStatusCallback()
        Session.getCurrentSession().addCallback(kakaoCallback)
        Session.getCurrentSession().checkAndImplicitOpen()


        appLoginBtn.setOnClickListener {
            moveToLoginActivity()
        }



    }

    private fun moveToLoginActivity() {
        startActivity(
            Intent(
                this@SimpleLoginActivity,
                LoginActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        )
    }

    private fun moveToMainActivity() {
        startActivity(
            Intent(
                this@SimpleLoginActivity,
                MainActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        )
    }

    private fun kakaoRequestMe() {
        val keys = arrayListOf<String>()
//        keys.add("properties.nickname")
//        keys.add("properties.profile_image")
//        keys.add("kakao_account.email")
        UserManagement.getInstance().me(keys, object : MeV2ResponseCallback() {
            override fun onSuccess(result: MeV2Response?) {


                /**
                 * @author KIYEON (giyeon15@gmail.com)
                 * @return MeV2Response
                 * @sample
                {
                    "id": "1267029130",
                    "connected_at": "2020-01-24T13:29:24Z",
                    "properties": {
                    "nickname": "기연",
                    "profile_image": "https://k.kakaocdn.net/dn/uzC48/btqzZ2hYKCW/ra45k4bkJbuxWvbaxkWklk/img_640x640.jpg",
                    "thumbnail_image": "https://k.kakaocdn.net/dn/uzC48/btqzZ2hYKCW/ra45k4bkJbuxWvbaxkWklk/img_110x110.jpg"
                },
                    "kakao_account": {
                    "profile_needs_agreement": false,
                    "profile": {
                    "nickname": "기연",
                    "thumbnail_image_url": "https://k.kakaocdn.net/dn/uzC48/btqzZ2hYKCW/ra45k4bkJbuxWvbaxkWklk/img_110x110.jpg",
                    "profile_image_url": "https://k.kakaocdn.net/dn/uzC48/btqzZ2hYKCW/ra45k4bkJbuxWvbaxkWklk/img_640x640.jpg"
                },
                    "has_email": true,
                    "email_needs_agreement": true,
                    "has_age_range": true,
                    "age_range_needs_agreement": true,
                    "has_birthday": true,
                    "birthday_needs_agreement": true,
                    "has_gender": true,
                    "gender_needs_agreement": true
                }
                }
*/




                Log.d("onSessionClosed", "success to update profile. msg = $result")

                moveToMainActivity()
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Log.d("onSessionClosed", "failed to update profile. msg = $errorResult")

            }

        })
    }

    inner class SessionStatusCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            Log.d(TAG, "SessionStatusCallback.onSessionOpenFailed exception:" + exception);
        }

        override fun onSessionOpened() {
            Log.d(TAG, "SessionStatusCallback.onSessionOpened")
            kakaoRequestMe()
        }

    }

}