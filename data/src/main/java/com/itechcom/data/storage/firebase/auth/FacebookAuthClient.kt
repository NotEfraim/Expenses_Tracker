package com.itechcom.data.storage.firebase.auth

import android.content.Intent
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.itechcom.data.model.DataFirebaseCallModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class FacebookAuthClient {

    val auth by lazy { FirebaseAuth.getInstance() }
    private val callBackManager by lazy { CallbackManager.Factory.create() }
    private val launchCoroutine by lazy { CoroutineScope(Dispatchers.IO) }
    suspend fun requestFacebookLogin(loginBtn: LoginButton) = flow {
        loginBtn.setPermissions("email", "public_profile")
        loginBtn.registerCallback(callBackManager, object : FacebookCallback<LoginResult>{
            override fun onCancel() {}

            override fun onError(error: FacebookException) {
                launchCoroutine.launch {
                    DataFirebaseCallModel(false, "${error.message}")
                }
            }

            override fun onSuccess(result: LoginResult) {
                launchCoroutine.launch{
                    emit(handleFacebookToken(result.accessToken))
                }
            }
        })
    }

    suspend fun handleFacebookToken(token : AccessToken) : DataFirebaseCallModel{
        val credential = FacebookAuthProvider.getCredential(token.token)
        return try {
            auth.signInWithCredential(credential).await()
            DataFirebaseCallModel(true, "")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, "${e.message}")
        }
    }

    fun startFacebookActivityResult(resultCode: Int, requestCode : Int, data : Intent?){
        callBackManager.onActivityResult(requestCode, resultCode, data)
    }
}