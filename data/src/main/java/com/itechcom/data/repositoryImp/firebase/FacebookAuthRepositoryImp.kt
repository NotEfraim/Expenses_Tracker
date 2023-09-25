package com.itechcom.data.repositoryImp.firebase

import com.itechcom.data.model.DataUserModel
import com.itechcom.data.repository.firebase.FacebookAuthRepository
import kotlinx.coroutines.flow.Flow

class FacebookAuthRepositoryImp : FacebookAuthRepository {
    override fun login(): Flow<DataUserModel> {
        TODO("Not yet implemented")
    }
}