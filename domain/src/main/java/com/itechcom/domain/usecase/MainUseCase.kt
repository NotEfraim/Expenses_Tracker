package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.DatabaseRepository
import com.itechcom.data.repository.firebase.LoginUtilRepository
import com.itechcom.data.storage.sharedpref.SharedPrefManager
import com.itechcom.domain.mapper.mapToFirebaseCallModelFlow
import com.itechcom.domain.mapper.userDataMap
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val sharedPrefManager: SharedPrefManager,
    private val loginUtilRepository: LoginUtilRepository
) {
    suspend fun getBasicAuthUser(email : String) =
        databaseRepository.getBasicAuthUser(email).mapToFirebaseCallModelFlow()

    suspend fun getSavePrefEmail(key : String) = sharedPrefManager.getString(key)
    suspend fun getLoginType(key : String) = sharedPrefManager.getString(key)

    suspend fun getLoggedInUser() = loginUtilRepository.getLoggedInUser().userDataMap()
}