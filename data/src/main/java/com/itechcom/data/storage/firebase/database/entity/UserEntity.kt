package com.itechcom.data.storage.firebase.database.entity

import com.google.firebase.database.Exclude

data class UserEntity(
    @get:Exclude
    var userId : String?,
    var name : String,
    var userName : String?,
    var userPassword : String?
)