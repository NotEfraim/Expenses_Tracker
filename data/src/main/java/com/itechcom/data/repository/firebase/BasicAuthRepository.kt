package com.itechcom.data.repository.firebase

import com.itechcom.data.model.DataFirebaseCallModel

interface BasicAuthRepository {
    suspend fun loginViaUserNameAndPass(email : String, password : String) : DataFirebaseCallModel
    suspend fun registerViaUserNameAndPass(email : String, password : String) : DataFirebaseCallModel
    suspend fun sendPasswordResetEmail(email: String) : DataFirebaseCallModel
}