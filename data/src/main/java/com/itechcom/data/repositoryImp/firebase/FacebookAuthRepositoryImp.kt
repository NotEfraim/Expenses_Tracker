package com.itechcom.data.repositoryImp.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.facebook.login.widget.LoginButton
import com.itechcom.data.repository.firebase.FacebookAuthRepository
import com.itechcom.data.storage.firebase.auth.FacebookAuthClient
import javax.inject.Inject

class FacebookAuthRepositoryImp @Inject constructor(
    private val facebookAuthClient: FacebookAuthClient
) : FacebookAuthRepository {
    override suspend fun requestFacebookLogin(login : LoginButton)
    = facebookAuthClient.requestFacebookLogin(login)

    override fun startFacebookActivityResult(resultCode: Int, requestCode : Int, data : Intent?) =
        facebookAuthClient.startFacebookActivityResult(resultCode, requestCode, data)

}