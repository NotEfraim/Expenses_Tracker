package com.itechcom.data.model

import com.google.gson.annotations.SerializedName

data class DataFirebaseCallModel (
    @SerializedName("isSuccess")
    var isSuccess : Boolean = false,
    @SerializedName("data")
    var data : Any? = null,
    @SerializedName("errorMessage")
    var errorMessage : String? = null
)