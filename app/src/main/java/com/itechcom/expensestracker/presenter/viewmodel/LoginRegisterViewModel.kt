package com.itechcom.expensestracker.presenter.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itechcom.domain.model.SignInResults
import com.itechcom.domain.usecase.LoginUtilUseCase
import com.itechcom.domain.usecase.LoginWithBasicAuthUseCase
import com.itechcom.domain.usecase.LoginWithFacebookUseCase
import com.itechcom.domain.usecase.LoginWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val loginWithBasicAuthUseCase: LoginWithBasicAuthUseCase,
    private val loginWithGoogleUseCase: LoginWithGoogleUseCase,
    private val loginWithFacebookUseCase: LoginWithFacebookUseCase,
    private val loginUtilUseCase: LoginUtilUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SignInResults())
    val state = _state.asStateFlow()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()
    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()


    init {
        viewModelScope.launch {
            addGoogleErrorAlertFlow()
            onAuthChange()
        }
    }


    /** Google Login */
    suspend fun requestGoogleSignIn() = loginWithGoogleUseCase.requestGoogleLogin()
    suspend fun getSignInWithIntent(intent : Intent){
        loginWithGoogleUseCase.googleGetSignInWithIntent(intent).collect{
            _state.value = it
        }
    }

    private suspend fun addGoogleErrorAlertFlow(){
        loginWithGoogleUseCase.addErrorMessageAlert(_errorMsg)
    }

    /** Login Util **/

    suspend fun signOut(action :() -> Unit) = loginUtilUseCase.sigOut { action.invoke() }
    suspend fun isAlreadySignedIn() : Boolean {
        val result = SignInResults(
            data = loginUtilUseCase.getLoggedInUser().single(),
            errorMsg = null
        )
        if(result.data == null) return false
        _state.value = result
        return true
    }

    private fun onAuthChange(){
        loginUtilUseCase.onAuthChange(_isLoggedIn)
    }

}