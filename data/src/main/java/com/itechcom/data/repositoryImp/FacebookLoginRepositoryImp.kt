package com.itechcom.data.repositoryImp

import com.itechcom.data.model.DataUserModel
import com.itechcom.data.repository.FacebookLoginRepository
import kotlinx.coroutines.flow.Flow

class FacebookLoginRepositoryImp : FacebookLoginRepository {
    override fun login(): Flow<DataUserModel> {
        TODO("Not yet implemented")
    }
}