package com.nexters.towhom.vo


//Login
data class TEST_LoginParams(val UserId: String, val UserPw: String)


data class TEST_LoginReturn(
    val result: String,
    var msg: String
) // W = wrongID, N = notPermission, E = networkError, S = success