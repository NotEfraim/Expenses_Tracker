package com.itechcom.data.storage.firebase.auth

import android.util.Log
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.itechcom.data.model.DataUserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class LoginUtil @Inject constructor(
    private val oneTapSignClient : SignInClient,
) {

    val auth = Firebase.auth
    fun validateUserIfLoggedIn() : String?{
        val user = auth.currentUser
        return user?.displayName
    }

    suspend fun logoutUser(action: (() -> Unit?)? = null) : Boolean {
        return try {
            action?.invoke()
            oneTapSignClient.signOut()
            auth.signOut()
            true
        }catch (e : Exception){
            Log.d("logoutUser", "logoutUser: ${e.message}")
            if(e is CancellationException) throw e
            false
        }
    }

    suspend fun getLoggedInUser() : DataUserData? = auth.currentUser?.run {
        DataUserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    fun onAuthChange(
        state : MutableStateFlow<Boolean>
    ){
        auth.addAuthStateListener {
            val user = it.currentUser
            state.value = user?.isAnonymous == false
        }
    }


}