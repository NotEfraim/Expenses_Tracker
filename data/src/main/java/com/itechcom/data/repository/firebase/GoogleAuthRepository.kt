package com.itechcom.data.repository.firebase

import android.content.Intent
import android.content.IntentSender
import com.itechcom.data.model.DataSignInResults
import com.itechcom.data.model.DataUserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface GoogleAuthRepository {
    suspend fun requestLogin() : IntentSender?
    suspend fun getSignInWithIntent(intent : Intent) : Flow<DataSignInResults?>
    suspend fun addErrorMessageAlert(errorFlow : MutableStateFlow<String>)
}