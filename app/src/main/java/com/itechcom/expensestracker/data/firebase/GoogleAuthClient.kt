package com.itechcom.expensestracker.data.firebase

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.itechcom.expensestracker.R
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
    suspend fun signIn() : IntentSender?{
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

    private fun buildSignInRequest() : BeginSignInRequest{
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.serverId))
                    .build()

            )
            .setAutoSelectEnabled(true)
            .build()
    }

    suspend fun getSignInResultFromIntent(intent : Intent) : SignInResults{
        val credential = oneTapSignClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            SignInResults(
                data = user?.run {
                    UserData(
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
            SignInResults(
                data = null,
                errorMsg = e.message
            )
        }

    }

    fun getSignedInUser() : UserData? = auth.currentUser?.run {
        UserData(
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

    fun addErrorMessageAlert(errorMsg : MutableStateFlow<String>){
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