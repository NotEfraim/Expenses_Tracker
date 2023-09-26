package com.itechcom.expensestracker.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.itechcom.domain.model.database.UserEntity
import com.itechcom.domain.usecase.DatabaseUseCase
import com.itechcom.domain.usecase.RegisterWithBasicAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterWithBasicAuthUseCase,
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    suspend fun registerWithEmailAndPassword(email : String, password : String) =
        registerUseCase.registerViaEmailAndPassword(email, password)

    suspend fun addUser(userEntity: UserEntity) = databaseUseCase.addUser(userEntity)

    fun saveEmailToPref(key : String, email: String) = registerUseCase.saveEmailToPref(key, email)
    fun saveLoginType(key : String, loginType: String) = registerUseCase.saveLoginType(key, loginType)

}