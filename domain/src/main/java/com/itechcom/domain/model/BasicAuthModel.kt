package com.itechcom.domain.model

import com.google.gson.annotations.SerializedName

data class BasicAuthModel (
    @SerializedName("isSuccess")
    var isSuccess : Boolean = false,
    @SerializedName("errorMessage")
    var errorMessage : String? = null
)