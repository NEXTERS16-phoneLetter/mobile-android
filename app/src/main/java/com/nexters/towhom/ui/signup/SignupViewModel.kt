package com.nexters.towhom.ui.signup

import androidx.lifecycle.MutableLiveData
import com.nexters.towhom.core.BaseViewModel
import com.nexters.towhom.ui.login.LoginHomeModel
import com.nexters.towhom.vo.TEST_LoginParams
import com.nexters.towhom.vo.TEST_LoginReturn

class SignupViewModel : BaseViewModel() {
    private val _pwInsertLiveData: MutableLiveData<String> = MutableLiveData()
    val pwInsertLiveData: MutableLiveData<String>
        get() = _pwInsertLiveData

    private val _pwInsertCheckLiveData: MutableLiveData<String> = MutableLiveData()
    val pwInsertCheckLiveData: MutableLiveData<String>
        get() = _pwInsertCheckLiveData


    //생각
//    // TODO : login interface에서 받아오는 값 Objects 자리에 대체
    private val _loginCheckLiveData: MutableLiveData<TEST_LoginReturn> = MutableLiveData()
    val loginCheckListData: MutableLiveData<TEST_LoginReturn>
        get() = _loginCheckLiveData

    fun clearEditText(clearTarget: String) {
        when (clearTarget) {
            "pwInsert" -> _pwInsertLiveData.value = ""
            "pwInsertCheck" -> _pwInsertCheckLiveData.value = ""
        }
    }


    // TODO: login interface 호출 및 처리함수
    // W = wrongID, N = notPermission, E = networkError, S = success
    fun trySignup() {
        /** TestCode */
        val response = LoginHomeModel().callNetwork(
            TEST_LoginParams(
                _pwInsertLiveData.value.toString(),
                _pwInsertCheckLiveData.value.toString()
            )
        )

        //생각
        _loginCheckLiveData.value = response
    }
}