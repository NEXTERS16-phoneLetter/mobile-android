package com.nexters.towhom.ui.signin

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentSigninBinding
import com.nexters.towhom.vo.CommonData
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SigninFragment : BindingFragment<FragmentSigninBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_signin

    private val vm by lazy { binding.viewModel!! }
    private val idEdit by lazy { binding.idEdit }
    private val pwEdit by lazy { binding.pwEdit }


    private val wrongTv by lazy { binding.wrongMsgTv }
    private val loginBtn by lazy { binding.loginBtn }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun bindingEventListener() {

    }

    override fun bindingObserver() {
        vm.idLiveData.observe(viewLifecycleOwner, Observer {
            isTextEmpty(binding.idClearBtn, it)
            checkAndUpdateLoginBtnEnabled()
            setWrongMessageHide()
        })

        vm.pwLiveData.observe(viewLifecycleOwner, Observer {
            isTextEmpty(binding.pwClearBtn, it)
            checkAndUpdateLoginBtnEnabled()
            setWrongMessageHide()
        })

        vm.loginCheckListData.observe(viewLifecycleOwner, Observer {
            updateAccordingNetworkResult(it.result)
        })
    }


    // W = wrongID, N = notPermission, E = networkError, S = success
    private fun updateAccordingNetworkResult(result: String) {
        when (result) {
            "W" -> setMsgAndShow(resources.getString(R.string.str_wrong_id_pw))
            "N" -> setMsgAndShow(resources.getString(R.string.str_wrong_user_permission))
            "E" -> setMsgAndShow(resources.getString(R.string.str_network_fail))
            "S" -> {
                setCommonDataUserInfo()
                findNavController().navigate(R.id.action_app_signin_to_home)
            }
        }
    }


    private fun setMsgAndShow(msg: String) {
        wrongTv.apply {
            visibility = View.VISIBLE
            text = msg
        }
    }

    private fun setCommonDataUserInfo() {
        CommonData.USERID = idEdit.text.toString()
    }


    private fun setWrongMessageHide() {
        if (wrongTv.isVisible) wrongTv.visibility = View.GONE
    }

    private fun checkAndUpdateLoginBtnEnabled() {
        loginBtn.isEnabled = idEdit.text!!.isNotEmpty() && pwEdit.text!!.isNotEmpty()
    }


    private fun isTextEmpty(view: AppCompatImageButton, text: String) {
        if (text.isNotEmpty()) view.visibility = View.VISIBLE
        else view.visibility = View.GONE
    }

    override fun bindingView() {

    }


}