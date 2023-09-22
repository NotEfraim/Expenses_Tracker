package com.itechcom.expensestracker.data.firebase

data class SignInResults(
    var data : UserData?,
    val errorMsg : String? = ""
)

data class UserData(
    val userId : String?,
    val username : String?,
    val profilePictureUrl : String?
)