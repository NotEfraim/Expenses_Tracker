package com.itechcom.expensestracker.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.itechcom.domain.model.database.UserEntity
import com.itechcom.domain.usecase.MainUseCase
import com.itechcom.expensestracker.utils.LoginType
import com.itechcom.expensestracker.utils.LoginType.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
) : ViewModel() {

    private val _userName= MutableStateFlow("")
    val userName = _userName.asStateFlow()


    suspend fun getSignInUser(loginType : String? ,email: String){
        when(loginType){
            BASIC.name -> {
                getBasicAuthUserName(email)
            }
            GOOGLE.name -> {
                getGoogleLoginUserName()
            }
            FACEBOOK.name -> {

            }
        }
    }

    private suspend fun getBasicAuthUserName(email : String) {
        mainUseCase.getBasicAuthUser(email).collect {
            val data = it.data
            Log.d("testMeXX", "${data}")
            if(data is UserEntity){
                _userName.value = data.name?:"N/A"
            }
        }
    }

    private suspend fun getGoogleLoginUserName(){
        mainUseCase.getLoggedInUser().collect{
            _userName.value = it.username?:""
        }
    }

    suspend fun getSharedPrefEmail(key : String) = mainUseCase.getSavePrefEmail(key)
    suspend fun getLoginType(key : String) = mainUseCase.getLoginType(key)

    suspend fun logoutUser(func :() -> Unit) = mainUseCase.logoutUser(func)


}