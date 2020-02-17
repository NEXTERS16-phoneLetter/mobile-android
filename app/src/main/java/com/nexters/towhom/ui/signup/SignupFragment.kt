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
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : BindingFragment<FragmentSignupBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_signup

    private val vm by lazy { binding.viewModel!! }
    private val emailInsert by lazy { binding.emailInsert }
    private val pwInsert by lazy { binding.pwInsert }

//    private val wrongTv by lazy { binding.wrongMsgTv }// 존재여부 잘 모르겠
    private val continueBtn by lazy { binding.continueBtn }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = getViewModel() //SignupViewModel()
        binding.lifecycleOwner = this
    }

    override fun bindingEventListener() {

        continue_btn.setOnClickListener {
            findNavController().navigate(R.id.action_signup_to_phone)
        }
    }

    override fun bindingObserver() {
        vm.emailInsertLiveData.observe(viewLifecycleOwner, Observer {
            isTextEmpty(binding.emailInsertClearBtn, it)
            checkAndUpdateSignupBtnEnabled()
//            setWrongMessageHide()
        })

        vm.pwInsertLiveData.observe(viewLifecycleOwner, Observer {
            isTextEmpty(binding.pwInsertClearBtn, it)
            checkAndUpdateSignupBtnEnabled()
//            setWrongMessageHide()
        })

        vm.loginCheckListData.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_signup_to_phone)

//            updateAccordingNetworkResult(it.result)
        })
    }


    // W = wrongPw_length, N = notPermission, E = networkError, S = success, D = wrongPw_not same
    private fun updateAccordingNetworkResult(result: String) {
        when (result) {
//            "W" -> setMsgAndShow(resources.getString(R.string.str_wrong_length_pw))
//            "D" -> setMsgAndShow(resources.getString(R.string.str_wrong_same_pw))
//            "N" -> setMsgAndShow(resources.getString(R.string.str_wrong_user_permission))
//            "E" -> setMsgAndShow(resources.getString(R.string.str_network_fail))
            "S" -> {
                setCommonDataUserInfo()
                findNavController().navigate(R.id.action_signup_to_phone)
            }
        }
    }


//    private fun setMsgAndShow(msg: String) {
//        wrongTv.apply {
//            visibility = View.VISIBLE
//            text_default = msg
//        }
//    }

    private fun setCommonDataUserInfo() {
        CommonData.USERID = pwInsert.text.toString()
    }


//    private fun setWrongMessageHide() {
//        if (wrongTv.isVisible) wrongTv.visibility = View.GONE
//    }

    private fun checkAndUpdateSignupBtnEnabled() {
        continueBtn.isEnabled = emailInsert.text!!.isNotEmpty() && pwInsert.text!!.isNotEmpty()
    }


    private fun isTextEmpty(view: AppCompatImageButton, text: String) {
        if (text.isNotEmpty()) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }

    override fun bindingView() {

    }



}