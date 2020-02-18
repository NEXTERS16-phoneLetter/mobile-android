package com.nexters.towhom.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexters.towhom.core.BaseViewModel
import com.nexters.towhom.ui.login.LoginHomeModel
import com.nexters.towhom.vo.TEST_LoginParams
import com.nexters.towhom.vo.TEST_LoginReturn

class PhoneCertificationViewModel : BaseViewModel() {
    private val _phoneInsertLiveData: MutableLiveData<String> = MutableLiveData()
    val phoneInsertLiveData: MutableLiveData<String>
        get() = _phoneInsertLiveData

    private val _phoneCertificationLiveData: MutableLiveData<String> = MutableLiveData()
    val phoneCertificationLiveData: MutableLiveData<String>
        get() = _phoneCertificationLiveData;

    //생각
//    // TODO : login interface에서 받아오는 값 Objects 자리에 대체
    private val _loginCheckLiveData: MutableLiveData<TEST_LoginReturn> = MutableLiveData()
    val loginCheckListData: LiveData<TEST_LoginReturn>
        get() = _loginCheckLiveData

    fun clearEditText(clearTarget: String) {
        when (clearTarget) {
            "phoneClearBtn" -> _phoneInsertLiveData.value = ""
            "phoneCertificationBtn" -> _phoneCertificationLiveData.value = ""
        }
    }


    // TODO: login interface 호출 및 처리함수
    // W = wrongID, N = notPermission, E = networkError, S = success
    fun trySignup() {
        /** TestCode */
        val response = LoginHomeModel().callNetwork(
            TEST_LoginParams(
                _phoneInsertLiveData.value.toString(),
                phoneCertificationLiveData.value.toString()
            )
        )

        //생각
        _loginCheckLiveData.value = response
    }
}