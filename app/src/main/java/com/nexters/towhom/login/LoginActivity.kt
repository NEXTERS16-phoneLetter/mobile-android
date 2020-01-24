package com.nexters.towhom.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingActivity
import com.nexters.towhom.databinding.ActivityLoginBinding
import com.nexters.towhom.main.MainActivity
import com.nexters.towhom.vo.CommonData
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginActivity : BindingActivity<ActivityLoginBinding>() {
    override fun getLayoutResId(): Int = R.layout.activity_login

    private val vm by lazy { binding.viewModel!! }
    private val idEdit by lazy { binding.idEdit }
    private val pwEdit by lazy { binding.pwEdit }


    private val wrongTv by lazy { binding.wrongMsgTv }
    private val loginBtn by lazy { binding.loginBtn }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = getViewModel()
        binding.lifecycleOwner = this

        vm.idLiveData.observe(this, Observer {
            isTextEmpty(binding.idClearBtn, it)
            checkAndUpdateLoginBtnEnabled()
            setWrongMessageHide()
        })

        vm.pwLiveData.observe(this, Observer {
            isTextEmpty(binding.pwClearBtn, it)
            checkAndUpdateLoginBtnEnabled()
            setWrongMessageHide()
        })

        vm.loginCheckListData.observe(this, Observer {
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
                moveToMainActity()
            }
        }
    }


    private fun setMsgAndShow(msg: String) {
        wrongTv.apply {
            visibility = View.VISIBLE
            text = msg
        }
    }

    private fun moveToMainActity() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
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
}