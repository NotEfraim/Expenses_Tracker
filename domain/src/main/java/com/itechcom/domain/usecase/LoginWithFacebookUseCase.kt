package com.itechcom.domain.usecase

import android.content.Intent
import com.facebook.login.widget.LoginButton
import com.itechcom.data.repository.firebase.FacebookAuthRepository
import com.itechcom.domain.mapper.mapToFirebaseCallModelFlow
import javax.inject.Inject

class LoginWithFacebookUseCase @Inject constructor(
    private val facebookAuthRepository: FacebookAuthRepository
) {
    suspend fun requestLoginWithFacebook(login : LoginButton)
    = facebookAuthRepository.requestFacebookLogin(login).mapToFirebaseCallModelFlow()

    fun startFacebookActivityResult(resultCode: Int, requestCode : Int, data : Intent?) =
        facebookAuthRepository.startFacebookActivityResult(resultCode, requestCode, data)

}