package com.itechcom.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.itechcom.data.storage.room.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun saveUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteSavedUser(userEntity: UserEntity)

    @Query("SELECT * FROM USER_TABLE")
    suspend fun getUserInfo() : UserEntity
}