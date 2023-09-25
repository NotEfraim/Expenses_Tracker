package com.itechcom.data.repositoryImp.firebase

import com.itechcom.data.model.DataUserData
import com.itechcom.data.repository.firebase.LoginUtilRepository
import com.itechcom.data.storage.firebase.auth.LoginUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUtilRepositoryImp @Inject constructor(
    private val loginUtil: LoginUtil
) : LoginUtilRepository {
    override suspend fun getLoggedInUser(): Flow<DataUserData?> = flow {
        emit(loginUtil.getLoggedInUser())
    }

    override suspend fun logoutUser(action: (() -> Unit?)?) {
        return loginUtil.logoutUser(action)
    }

    override fun validateUserIfLoggedIn(): String? {
        return loginUtil.validateUserIfLoggedIn()
    }

    override fun onAuthChange(state: MutableStateFlow<Boolean>) {
        loginUtil.onAuthChange(state)
    }


}