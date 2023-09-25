package com.itechcom.data.model

import com.google.gson.annotations.SerializedName

data class DataBasicAuthModel (
    @SerializedName("isSuccess")
    var isSuccess : Boolean = false,
    @SerializedName("errorMessage")
    var errorMessage : String? = null
)