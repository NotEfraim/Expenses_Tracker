package com.itechcom.domain.model.auth

import com.google.gson.annotations.SerializedName

data class FirebaseCallModel (
    @SerializedName("isSuccess")
    var isSuccess : Boolean = false,
    @SerializedName("data")
    var data : Any? = null,
    @SerializedName("errorMessage")
    var errorMessage : String? = null
)