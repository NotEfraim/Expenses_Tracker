package com.itechcom.expensestracker.presenter

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.itechcom.expensestracker.data.firebase.GoogleAuthClient
import com.itechcom.expensestracker.data.firebase.SignInResults
import com.itechcom.expensestracker.data.room.RoomDBManager
import com.itechcom.expensestracker.data.sharedpref.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SingleViewModel @Inject constructor(
    val roomDBManager: RoomDBManager,
    val sharedPrefManager: SharedPrefManager,
    val googleAuthClient: GoogleAuthClient
) : ViewModel() {

    private val _state = MutableStateFlow(SignInResults(data = null))
    val state = _state.asStateFlow()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()
    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    init {
        googleStates()
    }

    suspend fun requestGoogleSignIn() =  googleAuthClient.signIn()
    suspend fun getSignInWithIntent(intent : Intent){
        val result = googleAuthClient.getSignInResultFromIntent(intent)
        _state.value = result
    }
    suspend fun googleSignOut() {
        googleAuthClient.signOut()
    }

    fun isAlreadySignedIn() : Boolean {
        val result = SignInResults(
            data = googleAuthClient.getSignedInUser(),
            errorMsg = null
        )
        if(result.data == null) return false
        _state.value = result
        return true
    }

    private fun googleStates(){
        googleAuthClient.onAuthChange(_isLoggedIn)
        googleAuthClient.addErrorMessageAlert(_errorMsg)
    }

}