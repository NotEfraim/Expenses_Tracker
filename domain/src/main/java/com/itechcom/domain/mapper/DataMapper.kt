package com.itechcom.domain.mapper

import com.itechcom.data.model.DataBasicAuthModel
import com.itechcom.data.model.DataSignInResults
import com.itechcom.data.model.DataUserData
import com.itechcom.domain.model.BasicAuthModel
import com.itechcom.domain.model.SignInResults
import com.itechcom.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


suspend fun Flow<DataUserData?>.userDataMap() : Flow<UserData> = flow {
    val model = UserData()
    this@userDataMap.collect {
        it?.run {
            model.userId = it.userId
            model.username = it.username
            model.profilePictureUrl = it.profilePictureUrl
        }
    }
    emit(model)
}

suspend fun Flow<DataSignInResults?>.signInResultsMap() = flow {
    val model = SignInResults()
    this@signInResultsMap.collect {
        it?.run {
            model.data = UserData(
                it.data?.userId,
                it.data?.username,
                it.data?.profilePictureUrl
            )
            model.errorMsg = it.errorMsg
        }
    }
    emit(model)
}

fun DataBasicAuthModel.mapToBasicAuthModel() : BasicAuthModel{
    return BasicAuthModel(isSuccess, errorMessage)
}