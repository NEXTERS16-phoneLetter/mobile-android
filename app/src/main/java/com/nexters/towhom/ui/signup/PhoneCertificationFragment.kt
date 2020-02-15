package com.nexters.towhom.ui.signup

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentSignupBinding
import com.nexters.towhom.vo.CommonData
import org.koin.androidx.viewmodel.ext.android.getViewModel
import androidx.lifecycle.Observer
import com.nexters.towhom.databinding.FragmentPhoneCertificationBinding

class PhoneCertificationFragment : BindingFragment<FragmentPhoneCertificationBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_phone_certification

    private val vm by lazy { binding.viewModel!! }
    private val phoneInsert by lazy { binding.phoneInsertEt }
    private val phoneCertificationInsert by lazy { binding.phoneCertificationNumberInsert }

//    private val wrongTv by lazy { binding.wrongMsgTv }// 존재여부 잘 모르겠
    private val signupBtn by lazy { binding.signupBtn}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = getViewModel() //SignupViewModel()
        binding.lifecycleOwner = this
    }

    override fun bindingEventListener() {

    }

    override fun bindingObserver() {
//        vm.phoneInsertLiveData.observe(viewLifecycleOwner, Observer {
//            isTextEmpty(binding.phoneInsertEt, it)
//            checkAndUpdateSignupBtnEnabled()
////            setWrongMessageHide()
//        })

        vm.pwInsertCheckLiveData.observe(viewLifecycleOwner, Observer {
            isTextEmpty(binding.pwInsertCheckClearBtn, it)
            checkAndUpdateSignupBtnEnabled()
//            setWrongMessageHide()
        })

        // 생
        vm.loginCheckListData.observe(viewLifecycleOwner, Observer {
            updateAccordingNetworkResult(it.result)
        })
    }


    // W = wrongPw_length, N = notPermission, E = networkError, S = success, D = wrongPw_not same
    private fun updateAccordingNetworkResult(result: String) {
//        when (result) {
////            "W" -> setMsgAndShow(resources.getString(R.string.str_wrong_length_pw))
////            "D" -> setMsgAndShow(resources.getString(R.string.str_wrong_same_pw))
////            "N" -> setMsgAndShow(resources.getString(R.string.str_wrong_user_permission))
////            "E" -> setMsgAndShow(resources.getString(R.string.str_network_fail))
////            "S" -> {
////                setCommonDataUserInfo()
////                findNavController().navigate(R.id.action_app_signin_to_home)
////            }
//        }
    }


//    private fun setMsgAndShow(msg: String) {
//        wrongTv.apply {
//            visibility = View.VISIBLE
//            text_default = msg
//        }
//    }

    private fun setCommonDataUserInfo() {
//        CommonData.USERID = pwInsert.text.toString()
    }
    //이 userid는 뭐야? commondata가 뭔지도 모르겠읍니


//    private fun setWrongMessageHide() {
//        if (wrongTv.isVisible) wrongTv.visibility = View.GONE
//    }

    private fun checkAndUpdateSignupBtnEnabled() {
//        continueBtn.isEnabled = pwInsert.text!!.isNotEmpty() && pwInsert.text!!.isNotEmpty()
    }


    private fun isTextEmpty(view: AppCompatImageButton, text: String) {
        if (text.isNotEmpty()) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }

    override fun bindingView() {

    }



}