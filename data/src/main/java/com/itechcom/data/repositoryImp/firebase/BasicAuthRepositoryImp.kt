package com.itechcom.data.repositoryImp.firebase

import com.itechcom.data.repository.firebase.BasicAuthRepository
import com.itechcom.data.storage.firebase.auth.BasicAuthClient
import javax.inject.Inject

class BasicAuthRepositoryImp @Inject constructor(
    private val basicAuthClient: BasicAuthClient
): BasicAuthRepository {
    override suspend fun loginViaUserNameAndPass(email : String, password : String) =
        basicAuthClient.signInViaEmailAndPassword(email, password)
    override suspend fun registerViaUserNameAndPass(email : String, password : String) =
        basicAuthClient.registerVieEmailAndPassword(email, password)
}