package com.itechcom.data.repository

import com.itechcom.data.model.DataUserModel
import kotlinx.coroutines.flow.Flow

interface FacebookLoginRepository {
    fun login() : Flow<DataUserModel>
}