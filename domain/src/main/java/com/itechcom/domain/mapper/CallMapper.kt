package com.itechcom.domain.mapper

import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
import com.itechcom.domain.model.auth.FirebaseCallModel
import com.itechcom.domain.model.auth.UserData
import com.itechcom.domain.model.database.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun DataFirebaseCallModel.mapToFirebaseCallModel() : FirebaseCallModel {
    return FirebaseCallModel(isSuccess, errorMessage)
}

fun Flow<DataFirebaseCallModel>.mapToFirebaseCallModelFlow() = flow{
    this@mapToFirebaseCallModelFlow.collect{
        val data = it.data
        if(data is DataUserEntity) {
            emit(FirebaseCallModel(it.isSuccess, UserEntity(
                name = data.name,
                userName = data.userName
            ), it.errorMessage))
        }
    }
}