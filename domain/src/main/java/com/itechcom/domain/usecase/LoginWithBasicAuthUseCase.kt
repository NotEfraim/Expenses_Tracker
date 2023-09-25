package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.BasicAuthRepository
import javax.inject.Inject

class LoginWithBasicAuthUseCase @Inject constructor(
    private val basicAuthRepository: BasicAuthRepository
) {

    /** Firebase Basic Login */
    suspend fun loginViaEmailAndPassword(email : String, password : String) : Pair<Boolean, String>  {
        return basicAuthRepository.loginViaUserNameAndPass(email, password)
    }

    suspend fun registerViaEmailAndPassword(email : String, password : String) : Pair<Boolean, String>  {
        return basicAuthRepository.registerViaUserNameAndPass(email, password)
    }
}