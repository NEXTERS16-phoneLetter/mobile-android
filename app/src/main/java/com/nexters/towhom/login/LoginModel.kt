package com.nexters.towhom.login

import com.nexters.towhom.vo.TEST_LoginParams
import com.nexters.towhom.vo.TEST_LoginReturn

class LoginModel {

    // TODO: LOGIN INTERFACE로 변경
    // W = wrongID, N = notPermission, E = networkError, S = success
    fun callNetwork(params: TEST_LoginParams) : TEST_LoginReturn {
        return when(params.UserId) {
            "wc" -> TEST_LoginReturn("W", "msg1")
            "pc" -> TEST_LoginReturn("N", "msg2")
            "ne" -> TEST_LoginReturn("E", "msg3")
            else -> TEST_LoginReturn("S", "msg4")
        }
    }
}