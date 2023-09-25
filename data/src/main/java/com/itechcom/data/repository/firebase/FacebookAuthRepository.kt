package com.itechcom.data.repository.firebase

import com.itechcom.data.model.DataUserModel
import kotlinx.coroutines.flow.Flow

interface FacebookAuthRepository {
    fun login() : Flow<DataUserModel>
}