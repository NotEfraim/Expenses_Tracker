package com.itechcom.data.repository.firebase

import com.itechcom.data.model.DataUserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface LoginUtilRepository {

    suspend fun getLoggedInUser() : Flow<DataUserData?>
    suspend fun logoutUser(action: (() -> Unit?)? = null) : Boolean
    fun validateUserIfLoggedIn() : String?

    fun onAuthChange(state : MutableStateFlow<Boolean>)
}