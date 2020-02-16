package com.nexters.towhom.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentLoginHomeBinding
import com.nexters.towhom.vo.CommonData
import org.koin.androidx.viewmodel.ext.android.getViewModel


/** @author Kiyeon kim
 * @issue lazy pattern -> fragment before() --> appLoginBtn은 이미 할당되어있는상태 ==> 다른 주소값을 바라보고 있음
 * @solve lateinit var 로 변경
 * */
class LoginHomeFragment : BindingFragment<FragmentLoginHomeBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_login_home

    private lateinit var appLoginBtn: AppCompatButton
    private lateinit var signInBtn: AppCompatButton
    private val TAG = LoginHomeFragment::class.java.simpleName
    private lateinit var lookAround : AppCompatButton


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = getViewModel()


        val kakaoCallback = SessionStatusCallback()
        Session.getCurrentSession().addCallback(kakaoCallback)
        Session.getCurrentSession().checkAndImplicitOpen()


    }

    /*  private fun moveToLoginActivity() {
          startActivity(
              Intent(
                  this@SimpleLoginActivity,
                  LoginActivity::class.java
              ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
          )
      }*/

    /*private fun moveToMainActivity() {
        startActivity(
            Intent(
                this@SimpleLoginActivity,
                MainActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        )
    }*/

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
                if (result != null) {
                    setUserProfile(result)
                }

                findNavController().navigate(R.id.action_login_to_home)
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Log.d("onSessionClosed", "failed to update profile. msg = $errorResult")

            }

        })
    }

    private fun setUserProfile(result: MeV2Response) {
        CommonData.USERID = result.id.toString() // kakao에서 제공해주는 고유값 임
        CommonData.USER_NAME = result.properties["nickname"] ?: "Empty Name" //TODO : EMPTY NAME 에서 "" 로 바꿔야 할지 고민필요
        CommonData.USER_IMAGE_URL =
            result.properties["thumbnail_image"] ?: "" // 110x 110 jpg  TODO: Default Image URL로 변경 요망


    }

    inner class SessionStatusCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            Log.d(TAG, "SessionStatusCallback.onSessionOpenFailed exception:" + exception)
        }

        override fun onSessionOpened() {
            Log.d(TAG, "SessionStatusCallback.onSessionOpened")
            kakaoRequestMe()
        }

    }

    override fun bindingView() {
        appLoginBtn = binding.appLoginBtn // view!!.findViewById<AppCompatButton>(R.id.app_login_btn)
        signInBtn = binding.signInBtn
        lookAround = binding.btnLookaround

    }

    override fun bindingEventListener() {
        lookAround.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_home)
        }

        appLoginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_login_home_to_app_login)
        }
        signInBtn.setOnClickListener {
            findNavController().navigate(R.id.action_login_home_to_signin)
        }
    }

    override fun bindingObserver() {

    }


}