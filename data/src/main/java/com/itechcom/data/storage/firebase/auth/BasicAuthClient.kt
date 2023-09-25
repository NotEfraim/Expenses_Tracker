package com.itechcom.data.storage.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.itechcom.data.model.DataBasicAuthModel
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class BasicAuthClient {

    private val auth = FirebaseAuth.getInstance()
    suspend fun signInViaEmailAndPassword(email : String, password : String) : DataBasicAuthModel{
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            DataBasicAuthModel(true, "")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataBasicAuthModel(false, validateError(e.message))
        }
    }

    suspend fun registerVieEmailAndPassword(email: String, password: String) : DataBasicAuthModel {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            DataBasicAuthModel(true, "")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataBasicAuthModel(false, "${e.message}")
        }
    }

    suspend fun sendPasswordResetEmail(email: String) : DataBasicAuthModel{
        return try {
            auth.sendPasswordResetEmail(email).await()
            DataBasicAuthModel(true, "")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataBasicAuthModel(false, "${e.message}")
        }
    }

    private fun validateError(e : String?) : String {
        if(e?.contains("INVALID_LOGIN_CREDENTIALS") == true)
            return "Email or password in incorrect."
        return e?:""
    }
}