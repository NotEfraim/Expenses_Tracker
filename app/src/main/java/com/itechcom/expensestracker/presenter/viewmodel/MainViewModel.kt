package com.itechcom.expensestracker.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.itechcom.domain.model.database.UserEntity
import com.itechcom.domain.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
) : ViewModel() {

    private val _userName= MutableStateFlow("")
    val userName = _userName.asStateFlow()

    suspend fun getBasicAuthUserName(email : String) {
        mainUseCase.getBasicAuthUser(email).collect {
            val data = it.data
            Log.d("testMeXX", "${data}")
            if(data is UserEntity){
                _userName.value = data.name?:"N/A"
            }
        }
    }

    suspend fun getSharedPrefEmail(key : String) = mainUseCase.getSavePrefEmail(key)

}