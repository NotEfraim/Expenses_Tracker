package com.itechcom.expensestracker.presenter.activity

import android.app.Activity
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.SignInResults
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityLoginBinding
import com.itechcom.expensestracker.presenter.viewmodel.LoginRegisterViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.intentTo
import com.itechcom.expensestracker.utils.extensions.showLoadingDialog
import com.itechcom.expensestracker.utils.extensions.toastUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginRegisterViewModel>(
    ActivityLoginBinding::inflate,
    LoginRegisterViewModel::class
) {

    override fun LoginRegisterViewModel.initObserver() {
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
    }
    private fun userState(isLoggedIn : Boolean){
        if(isLoggedIn){
            intentTo<MainActivity> {  }
            finish()
        }
    }

    private fun onLoginResult(result : SignInResults){}

    private fun errorToaster(e : String){
        if(e.isNotEmpty()) toastUtil(e)
    }


    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ){ result ->
        supportFragmentManager.showLoadingDialog()
        if(result.resultCode == Activity.RESULT_OK){
            lifecycleScope.launch {
                viewModel.getSignInWithIntent(result.data?:return@launch)
            }
        }
    }

    private fun googleSignInFunc() = binding.apply {
        lifecycleScope.launch {
            val signInIntentSender = viewModel.requestGoogleSignIn()
            signInLauncher.launch(IntentSenderRequest.Builder(signInIntentSender ?: return@launch).build())
        }
    }
}