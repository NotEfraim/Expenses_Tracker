package com.itechcom.data.repository.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.facebook.login.widget.LoginButton
import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.data.model.DataUserModel
import kotlinx.coroutines.flow.Flow

interface FacebookAuthRepository {
    suspend fun requestFacebookLogin(login : LoginButton) : Flow<DataFirebaseCallModel>
    fun startFacebookActivityResult(resultCode: Int, requestCode : Int, data : Intent?)
}