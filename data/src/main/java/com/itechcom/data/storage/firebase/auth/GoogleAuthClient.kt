package com.itechcom.data.storage.firebase.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.itechcom.data.model.DataSignInResults
import com.itechcom.data.model.DataUserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject


class GoogleAuthClient @Inject constructor(
    val context: Context,
    val oneTapSignClient : SignInClient
) {

    private val auth = Firebase.auth
    private var errorMsg : MutableStateFlow<String>? = null
    private val serverId = "357328283425-fusji4earq7a7mdrvgbdvc9649n17oc6.apps.googleusercontent.com"
    suspend fun signIn() : IntentSender?{
        if(auth.currentUser?.displayName == null){
            val result = try {
                oneTapSignClient.beginSignIn(buildSignInRequest()).await()
            }catch (e : Exception){
                validateError(e.message?:"")
                e.printStackTrace()
                if(e is CancellationException) throw e
                null
            }
            return result?.pendingIntent?.intentSender
        }
       return null
    }

    private fun buildSignInRequest() : BeginSignInRequest{
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(serverId)
                    .build()

            )
            .setAutoSelectEnabled(true)
            .build()
    }

    suspend fun getSignInResultFromIntent(intent : Intent) : DataSignInResults {
        val credential = oneTapSignClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            DataSignInResults(
                data = user?.run {
                    DataUserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMsg = null
            )

        }catch (e : Exception){
            validateError(e.message?:"")
            e.printStackTrace()
            if(e is CancellationException) throw e
            DataSignInResults(
                data = null,
                errorMsg = e.message
            )
        }

    }

    fun getSignedInUser() : DataUserData? = auth.currentUser?.run {
        DataUserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    suspend fun signOut(){
        try {
            oneTapSignClient.signOut().await()
            auth.signOut()
        }catch (e : Exception){
            validateError(e.message?:"")
            if(e is CancellationException) throw e
        }
    }

    fun onAuthChange(
        state : MutableStateFlow<Boolean>
        ){
        auth.addAuthStateListener {
            val user = it.currentUser
            state.value = user?.isAnonymous == false
        }
    }

    fun addErrorMessageAlert(){
        this.errorMsg = errorMsg
    }

    private fun validateError(error : String){
        this.errorMsg?.value =
            if(error.contains("blocked due to too many canceled sign-in prompts"))
                "blocked due to too many canceled sign-in prompts"
            else if(error.contains("auth.api.identity.signin.api is not available on this device"))
                "Google Sign in is not available on this device"
            else "Unknown Error"
    }

}