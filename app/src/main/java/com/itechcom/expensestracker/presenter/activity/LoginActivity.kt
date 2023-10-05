package com.itechcom.expensestracker.presenter.activity

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.auth.SignInResults
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityLoginBinding
import com.itechcom.expensestracker.presenter.viewmodel.LoginViewModel
import com.itechcom.expensestracker.utils.Constants
import com.itechcom.expensestracker.utils.LoginType
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.intentTo
import com.itechcom.expensestracker.utils.extensions.toastUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    ActivityLoginBinding::inflate,
    LoginViewModel::class
) {

    override fun LoginViewModel.initObserver() {
        collect(state,::onLoginResult)
        collect(isLoggedIn, ::userState)
        collect(errorMsg, ::errorToaster)
    }

    override fun ActivityLoginBinding.initialize() {
        initView()
    }

    private fun initView() =  binding.apply{
        createAccountBtn.setOnClickListener { intentTo<RegisterActivity> {  } }
        loginGoogleBtn.setOnClickListener {
            googleSignInFunc()
        }
        loginButton.setOnClickListener {
            showLoadingDialog()
            loggedInViaEmailAndPassword()
        }

        loginFacebookBtnFront.setOnClickListener {
            toastUtil("Coming soon...")
        }
        initFacebookLogin()

    }

    private fun initFacebookLogin() = viewModel.apply {
        lifecycleScope.launch {
            requestFacebookSignIn(binding.loginFacebookBtn).collectLatest{
                Log.d("initFacebookLogin", "$it")
            }
        }
    }

    private fun loggedInViaEmailAndPassword() = viewModel.apply {
        binding.apply {
            val email = email.getFieldText()
            val password = password.getFieldText()
            lifecycleScope.launch {
                val result = loginViaEmailAndPassword(email, password)
                if(!result.isSuccess){
                    hideLoadingDialog()
                    toastUtil("Email or password is incorrect.")
                }else{
                    saveEmailToPref(Constants.PREF_EMAIL, email.lowercase())
                    saveLoginType(Constants.LOGIN_TYPE, LoginType.BASIC.name)
                }
            }
        }
    }

    /** Observe if user logged in **/
    private fun userState(isLoggedIn : Boolean){
        if(isLoggedIn){
            showLoadingDialog()
            lifecycleScope.launch {
                delay(1500)
                intentTo<MainActivity> {  }
                finish()
            }
        }
    }

    private fun onLoginResult(result : SignInResults){}

    private fun errorToaster(e : String?){
        if(!e.isNullOrEmpty()) {
            hideLoadingDialog()
            toastUtil(e)
        }
    }


    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ){ result ->
        showLoadingDialog()
        if(result.resultCode == Activity.RESULT_OK){
            lifecycleScope.launch {
                result?.data?.let {
                    viewModel.getSignInWithIntent(it)
                    viewModel.saveLoginType(Constants.LOGIN_TYPE, LoginType.GOOGLE.name)
                }
            }
        }
    }

    private fun googleSignInFunc() = binding.apply {
        lifecycleScope.launch {
            val signInIntentSender = viewModel.requestGoogleSignIn()
            signInLauncher.launch(IntentSenderRequest.Builder(signInIntentSender ?: return@launch).build())
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.startFacebookActivityResult(resultCode, requestCode, data)

    }
}