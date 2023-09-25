package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.LoginUtilRepository
import com.itechcom.domain.mapper.userDataMap
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class LoginUtilUseCase @Inject constructor(
    private val loginUtilRepository: LoginUtilRepository
){
    suspend fun getLoggedInUser() = loginUtilRepository.getLoggedInUser().userDataMap()

    suspend fun sigOut(dialog :() -> Unit) = loginUtilRepository.logoutUser(dialog)

    suspend fun validateUserIfLoggedIn() = loginUtilRepository.validateUserIfLoggedIn()

    fun onAuthChange(state : MutableStateFlow<Boolean>) = loginUtilRepository.onAuthChange(state)

}