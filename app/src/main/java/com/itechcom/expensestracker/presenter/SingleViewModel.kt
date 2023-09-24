package com.itechcom.expensestracker.presenter

import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itechcom.domain.model.SignInResults
import com.itechcom.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SignInResults())
    val state = _state.asStateFlow()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()
    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()

    init {
        viewModelScope.launch {
            googleStates()
        }
    }


    /** Google Login */
    suspend fun requestGoogleSignIn() = loginUseCase.requestGoogleLogin()
    suspend fun getSignInWithIntent(intent : Intent){
        loginUseCase.googleGetSignInWithIntent(intent).collect{
            _state.value = it
        }
    }
    suspend fun googleSignOut() = loginUseCase.googleSignOut()

    suspend fun isAlreadySignedIn() : Boolean {
        val result = SignInResults(
            data = loginUseCase.getSignedInUser().single(),
            errorMsg = null
        )
        if(result.data == null) return false
        _state.value = result
        return true
    }

    private suspend fun googleStates(){
        loginUseCase.onAuthChange(_isLoggedIn)
        loginUseCase.addErrorMessageAlert()
    }

    /** Shared Preferences */

    fun setPrefValue(key : String, type : Any) = loginUseCase.setPrefValue(key, type)
    fun getPrefValue(key : String, type : Any) = loginUseCase.getPrefValue(key, type)


}