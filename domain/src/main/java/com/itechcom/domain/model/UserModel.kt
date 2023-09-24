package com.itechcom.domain.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("userName")
    var userName: String? = null,
    @SerializedName("userPassword")
    var userPassword : String? = null
)