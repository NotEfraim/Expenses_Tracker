package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.BasicAuthRepository
import com.itechcom.domain.mapper.mapToFirebaseCallModel
import com.itechcom.domain.model.auth.FirebaseCallModel
import javax.inject.Inject

class RegisterWithBasicAuthUseCase @Inject constructor(
    private val basicAuthRepository: BasicAuthRepository
){
    suspend fun registerViaEmailAndPassword(email : String, password : String) : FirebaseCallModel {
        return basicAuthRepository.registerViaUserNameAndPass(email, password).mapToFirebaseCallModel()
    }
}