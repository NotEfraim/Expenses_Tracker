package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.BasicAuthRepository
import com.itechcom.domain.mapper.mapToBasicAuthModel
import com.itechcom.domain.model.BasicAuthModel
import javax.inject.Inject

class RegisterWithBasicAuthUseCase @Inject constructor(
    private val basicAuthRepository: BasicAuthRepository
){
    suspend fun registerViaEmailAndPassword(email : String, password : String) : BasicAuthModel {
        return basicAuthRepository.registerViaUserNameAndPass(email, password).mapToBasicAuthModel()
    }
}