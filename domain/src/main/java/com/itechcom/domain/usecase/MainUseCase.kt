package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.DatabaseRepository
import com.itechcom.data.storage.sharedpref.SharedPrefManager
import com.itechcom.domain.mapper.mapToFirebaseCallModelFlow
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val sharedPrefManager: SharedPrefManager
) {
    suspend fun getBasicAuthUser(email : String) =
        databaseRepository.getBasicAuthUser(email).mapToFirebaseCallModelFlow()

    suspend fun getSavePrefEmail(key : String) = sharedPrefManager.getString(key)

}