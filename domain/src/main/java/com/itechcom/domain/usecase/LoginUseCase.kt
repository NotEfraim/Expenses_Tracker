package com.itechcom.domain.usecase

import android.content.Intent
import com.itechcom.data.repository.FacebookLoginRepository
import com.itechcom.data.repository.FirebaseLoginRepository
import com.itechcom.data.repository.GoogleLoginRepository
import com.itechcom.data.repository.SharedPrefRepository
import com.itechcom.data.storage.sharedpref.SharedPrefManager
import com.itechcom.domain.mapper.signInResultsMap
import com.itechcom.domain.mapper.userDataMap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val googleLoginRepository : GoogleLoginRepository,
    private val facebookLoginRepository: FacebookLoginRepository,
    private val firebaseLoginRepository: FirebaseLoginRepository,
    private val sharedPrefRepository: SharedPrefRepository
) {

    /** Shared Preferences */

   fun setPrefValue(key : String, type : Any) = sharedPrefRepository.setValue(key, type)
   fun getPrefValue(key : String, type : Any) = sharedPrefRepository.getValue(key, type)

    /** Google Login */
    suspend fun requestGoogleLogin() = googleLoginRepository.requestLogin()
    suspend fun googleGetSignInWithIntent(intent: Intent) =
        googleLoginRepository.getSignInWithIntent(intent).signInResultsMap()
    suspend fun googleSignOut() = googleLoginRepository.signOut()
    suspend fun getSignedInUser() = flow{
        emit(googleLoginRepository.getSignedInUser().userDataMap())
    }
    suspend fun onAuthChange(state : MutableStateFlow<Boolean>) =
        googleLoginRepository.onAuthChange(state)
    suspend fun addErrorMessageAlert() = googleLoginRepository.addErrorMessageAlert()

    /** Firebase Login */

}