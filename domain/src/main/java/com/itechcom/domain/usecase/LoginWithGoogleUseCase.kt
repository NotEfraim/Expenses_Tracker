package com.itechcom.domain.usecase

import android.content.Intent
import com.itechcom.data.repository.firebase.FacebookAuthRepository
import com.itechcom.data.repository.firebase.GoogleAuthRepository
import com.itechcom.data.repository.SharedPrefRepository
import com.itechcom.data.repository.firebase.BasicAuthRepository
import com.itechcom.domain.mapper.signInResultsMap
import com.itechcom.domain.mapper.userDataMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginWithGoogleUseCase @Inject constructor(
    private val googleLoginRepository : GoogleAuthRepository
) {

    /** Google Login */
    suspend fun requestGoogleLogin() = googleLoginRepository.requestLogin()
    suspend fun googleGetSignInWithIntent(intent: Intent) =
        googleLoginRepository.getSignInWithIntent(intent).signInResultsMap()
    suspend fun googleSignOut() = googleLoginRepository.signOut()
    suspend fun getSignedInUser() = flow{
        emit(googleLoginRepository.getSignedInUser().userDataMap())
    }
    suspend fun onAuthChange(state : MutableStateFlow<Boolean>) =
        googleLoginRepository.onAuthChange(state)
    suspend fun addErrorMessageAlert() = googleLoginRepository.addErrorMessageAlert()

}