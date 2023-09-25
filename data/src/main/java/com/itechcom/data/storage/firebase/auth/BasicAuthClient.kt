package com.itechcom.data.storage.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class BasicAuthClient {

    private val auth = FirebaseAuth.getInstance()
    suspend fun signInViaEmailAndPassword(email : String, password : String) : Pair<Boolean, String> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Pair(true, "")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            Pair(false, "${e.message}")
        }
    }

    suspend fun registerVieEmailAndPassword(email: String, password: String) : Pair<Boolean, String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Pair(true, "")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            Pair(false, "${e.message}")
        }
    }

    suspend fun sendPasswordResetEmail(email: String) : Pair<Boolean, String>{
        return try {
            auth.sendPasswordResetEmail(email).await()
            Pair(true, "")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            Pair(false, "${e.message}")
        }
    }
}