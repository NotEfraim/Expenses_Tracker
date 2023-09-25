package com.itechcom.domain.usecase

import com.itechcom.data.repository.SharedPrefRepository
import javax.inject.Inject

class SplashScreenUseCase @Inject constructor(
    private val sharedPrefRepository: SharedPrefRepository
) {

    /** Shared Preferences */

    fun sharedPrefGetBoolean(key : String, defValue : Boolean)
            = sharedPrefRepository.getBoolean(key, defValue)
    fun sharedPrefSetBoolean(key : String, value : Boolean) =
        sharedPrefRepository.setBoolean(key, value)
}