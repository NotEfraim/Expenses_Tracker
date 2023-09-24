package com.itechcom.data.model

import com.google.gson.annotations.SerializedName

data class DataUserModel(
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("userName")
    val userName: String? = null,
    @SerializedName("userPassword")
    val userPassword : String? = null
)