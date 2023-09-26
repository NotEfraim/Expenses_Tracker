package com.itechcom.data.storage.firebase.database.entity

import com.google.firebase.database.Exclude

data class DataUserEntity(
    @get:Exclude
    var userId : String? = null,
    var name : String? = null,
    var userName : String? = null,
    var userPassword : String? = null
)