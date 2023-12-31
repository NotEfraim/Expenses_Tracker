package com.itechcom.expensestracker.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.itechcom.domain.usecase.LoginUtilUseCase
import com.itechcom.domain.usecase.SplashScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val splashScreenUseCase: SplashScreenUseCase,
    private val loginUtilUseCase: LoginUtilUseCase
): ViewModel() {

    /** Shared Preferences */
    fun sharedPrefSetBoolean(key: String, value : Boolean)
            = splashScreenUseCase.sharedPrefSetBoolean(key, value)
    fun sharedPrefGetBoolean(key: String, defValue : Boolean)
            = splashScreenUseCase.sharedPrefGetBoolean(key, defValue)


    /** Login Util */
    suspend fun validateIfUserLoggedIn() = loginUtilUseCase.validateUserIfLoggedIn()

}