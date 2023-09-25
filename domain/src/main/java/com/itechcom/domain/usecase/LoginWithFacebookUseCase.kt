package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.GoogleAuthRepository
import javax.inject.Inject

class LoginWithFacebookUseCase @Inject constructor(
    private val googleLoginRepository : GoogleAuthRepository
) {


}