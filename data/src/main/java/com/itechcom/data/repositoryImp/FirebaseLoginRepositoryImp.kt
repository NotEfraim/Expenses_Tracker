package com.itechcom.data.repositoryImp

import com.itechcom.data.model.DataUserModel
import com.itechcom.data.storage.firebase.database.FirebaseDatabaseManager
import com.itechcom.data.repository.FirebaseLoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirebaseLoginRepositoryImp @Inject constructor(
    firebaseDatabaseManager: FirebaseDatabaseManager
): FirebaseLoginRepository {
    override fun login(): Flow<DataUserModel> {
        TODO("Not yet implemented")
    }
}