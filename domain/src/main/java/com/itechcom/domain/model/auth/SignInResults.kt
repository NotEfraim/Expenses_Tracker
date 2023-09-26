package com.itechcom.domain.model.auth

data class SignInResults(
    var data : UserData? = null,
    var errorMsg : String? = null
)

data class UserData(
    var userId : String? = null,
    var username : String? = null,
    var profilePictureUrl : String? = null
)