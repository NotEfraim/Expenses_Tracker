package com.itechcom.data.repository.firebase

interface BasicAuthRepository {
    suspend fun loginViaUserNameAndPass(email : String, password : String) : Pair<Boolean, String>
    suspend fun registerViaUserNameAndPass(email : String, password : String) : Pair<Boolean, String>
}