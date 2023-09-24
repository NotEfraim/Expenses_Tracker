package com.itechcom.data.model

data class DataSignInResults(
    var data : DataUserData?,
    val errorMsg : String? = ""
)

data class DataUserData(
    val userId : String?,
    val username : String?,
    val profilePictureUrl : String?
)