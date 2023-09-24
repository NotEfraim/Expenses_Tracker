package com.itechcom.data.repository

import android.content.Intent
import android.content.IntentSender
import com.itechcom.data.model.DataSignInResults
import com.itechcom.data.model.DataUserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface GoogleLoginRepository {
    suspend fun requestLogin() : IntentSender?
    suspend fun getSignInWithIntent(intent : Intent) : Flow<DataSignInResults?>
    suspend fun getSignedInUser() : Flow<DataUserData?>
    suspend fun signOut()
    suspend fun onAuthChange(state : MutableStateFlow<Boolean>)
    suspend fun addErrorMessageAlert()
}