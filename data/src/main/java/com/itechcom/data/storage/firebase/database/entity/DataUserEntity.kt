package com.itechcom.data.storage.firebase.database.entity

import com.google.firebase.database.Exclude
import com.google.gson.annotations.SerializedName

data class DataUserEntity(
    @get:Exclude
    @SerializedName("userId")
    var userId : String? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("userName")
    var userName : String? = null,
    @SerializedName("userPassword")
    var userPassword : String? = null
)