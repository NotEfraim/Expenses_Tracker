package com.itechcom.expensestracker.presenter.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itechcom.domain.model.SignInResults
import com.itechcom.domain.usecase.LoginWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleViewModel @Inject constructor(
    private val loginUseCase: LoginWithGoogleUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SignInResults())
    val state = _state.asStateFlow()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()
    private val _errorMsg = MutableStateFlow("")
    val errorMsg = _errorMsg.asStateFlow()





}