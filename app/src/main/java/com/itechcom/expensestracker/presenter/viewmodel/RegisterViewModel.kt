package com.itechcom.expensestracker.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.itechcom.domain.usecase.RegisterWithBasicAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterWithBasicAuthUseCase
) : ViewModel() {

    suspend fun registerWithEmailAndPassword(email : String, password : String) =
        registerUseCase.registerViaEmailAndPassword(email, password)

}