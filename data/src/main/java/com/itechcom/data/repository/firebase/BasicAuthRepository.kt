package com.itechcom.data.repository.firebase

import com.itechcom.data.model.DataBasicAuthModel

interface BasicAuthRepository {
    suspend fun loginViaUserNameAndPass(email : String, password : String) : DataBasicAuthModel
    suspend fun registerViaUserNameAndPass(email : String, password : String) : DataBasicAuthModel
    suspend fun sendPasswordResetEmail(email: String) : DataBasicAuthModel
}