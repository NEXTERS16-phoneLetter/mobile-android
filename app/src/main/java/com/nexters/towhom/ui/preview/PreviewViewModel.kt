package com.nexters.towhom.ui.preview

import androidx.lifecycle.MutableLiveData
import com.nexters.towhom.core.BaseViewModel
import com.nexters.towhom.ui.login.LoginHomeModel
import com.nexters.towhom.vo.TEST_LoginParams
import com.nexters.towhom.vo.TEST_LoginReturn

class PreviewViewModel : BaseViewModel() {

    private val _emailInsertLiveData: MutableLiveData<String> = MutableLiveData()
    val emailInsertLiveData: MutableLiveData<String>
        get() = _emailInsertLiveData


    //생각
//    // TODO : login interface에서 받아오는 값 Objects 자리에 대체
    private val _loginCheckLiveData: MutableLiveData<TEST_LoginReturn> = MutableLiveData()
    val loginCheckListData: MutableLiveData<TEST_LoginReturn>
        get() = _loginCheckLiveData

    fun clearEditText(clearTarget: String) {
        when (clearTarget) {
            "emailInsertClear" -> _emailInsertLiveData.value = ""
        }
    }

    fun trySend() {
        /** TestCode */
//        val response = LoginHomeModel().callNetwork(
//            TEST_LoginParams(
//                _emailInsertLiveData.value.toString(),
//                _pwInsertLiveData.value.toString()
//            )
//        )

        //생각
//        _loginCheckLiveData.value = response
    }

}