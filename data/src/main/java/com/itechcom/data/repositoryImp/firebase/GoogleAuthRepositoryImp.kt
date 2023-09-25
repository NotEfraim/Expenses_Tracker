package com.itechcom.data.repositoryImp.firebase

import android.content.Intent
import com.itechcom.data.storage.firebase.auth.GoogleAuthClient
import com.itechcom.data.repository.firebase.GoogleAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GoogleAuthRepositoryImp @Inject constructor(
    private val googleAuthClient: GoogleAuthClient
) : GoogleAuthRepository {
    override suspend fun requestLogin() = googleAuthClient.signIn()

    override suspend fun getSignInWithIntent(intent: Intent) = flow {
        emit(googleAuthClient.getSignInResultFromIntent(intent))
    }

    override suspend fun getSignedInUser() = flow {
        emit(googleAuthClient.getSignedInUser())
    }

    override suspend fun signOut() = googleAuthClient.signOut()

    override suspend fun onAuthChange(state : MutableStateFlow<Boolean>)
    = googleAuthClient.onAuthChange(state)

    override suspend fun addErrorMessageAlert() =
        googleAuthClient.addErrorMessageAlert()


}