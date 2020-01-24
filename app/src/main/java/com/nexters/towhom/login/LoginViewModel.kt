package com.nexters.towhom.login

import androidx.lifecycle.MutableLiveData
import com.nexters.towhom.core.BaseViewModel
import com.nexters.towhom.vo.TEST_LoginParams
import com.nexters.towhom.vo.TEST_LoginReturn

class LoginViewModel : BaseViewModel() {

    private val _idLiveData: MutableLiveData<String> = MutableLiveData()
    val idLiveData: MutableLiveData<String>
        get() = _idLiveData

    private val _pwLiveData: MutableLiveData<String> = MutableLiveData()
    val pwLiveData: MutableLiveData<String>
        get() = _pwLiveData

    // TODO : login interface에서 받아오는 값 Objects 자리에 대체
    private val _loginCheckLiveData: MutableLiveData<TEST_LoginReturn> = MutableLiveData()
    val loginCheckListData: MutableLiveData<TEST_LoginReturn>
        get() = _loginCheckLiveData

    fun clearEditText(clearTarget: String) {
        when (clearTarget) {
            "idClear" -> _idLiveData.value = ""
            "pwClear" -> _pwLiveData.value = ""
        }
    }


    // TODO: login interface 호출 및 처리함수
    // W = wrongID, N = notPermission, E = networkError, S = success
    fun tryLogin() {
        /** TestCode */
        val response = LoginModel().callNetwork(
            TEST_LoginParams(
                _idLiveData.value.toString(),
                _pwLiveData.value.toString()
            )
        )
        _loginCheckLiveData.value = response

    }
}