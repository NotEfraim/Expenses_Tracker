package com.itechcom.data.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var userId : Int?,
    @ColumnInfo(name = "name")
    var name : String,
    @ColumnInfo(name= "userName")
    var userName : String?,
    @ColumnInfo(name = "userPassword")
    var userPassword : String?
)