package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.BasicAuthRepository
import com.itechcom.data.storage.sharedpref.SharedPrefManager
import com.itechcom.domain.mapper.mapToFirebaseCallModel
import javax.inject.Inject

class LoginWithBasicAuthUseCase @Inject constructor(
    private val basicAuthRepository: BasicAuthRepository,
    private val sharedPrefManager: SharedPrefManager
) {

    /** Firebase Basic Login */
    suspend fun loginViaEmailAndPassword(email : String, password : String) =
        basicAuthRepository.loginViaUserNameAndPass(email, password).mapToFirebaseCallModel()

    suspend fun sendPasswordResetEmail(email: String) =
        basicAuthRepository.sendPasswordResetEmail(email).mapToFirebaseCallModel()

    fun saveEmailToPref(key : String, email: String) = sharedPrefManager.setString(key, email)
    fun saveLoginType(key : String, loginType: String) = sharedPrefManager.setString(key, loginType)

}