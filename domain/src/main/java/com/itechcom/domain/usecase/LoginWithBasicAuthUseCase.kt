package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.BasicAuthRepository
import com.itechcom.domain.mapper.mapToBasicAuthModel
import javax.inject.Inject

class LoginWithBasicAuthUseCase @Inject constructor(
    private val basicAuthRepository: BasicAuthRepository
) {

    /** Firebase Basic Login */
    suspend fun loginViaEmailAndPassword(email : String, password : String) =
        basicAuthRepository.loginViaUserNameAndPass(email, password).mapToBasicAuthModel()

    suspend fun sendPasswordResetEmail(email: String) =
        basicAuthRepository.sendPasswordResetEmail(email).mapToBasicAuthModel()

}