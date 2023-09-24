package com.itechcom.expensestracker.presenter.activity

import android.app.Activity
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.SignInResults
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityMainBinding
import com.itechcom.expensestracker.presenter.SingleViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.toastUtil
import com.itechcom.expensestracker.utils.extensions.transparentStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, SingleViewModel>(
    ActivityMainBinding::inflate,
    SingleViewModel::class)
{
    private var isUserSignedIn = false

    override fun SingleViewModel.initCall() {
        lifecycleScope.launch {
            isUserSignedIn = viewModel.isAlreadySignedIn()
        }
    }

    override fun SingleViewModel.initObserver() {
        collect(state,::onLoginResult)
        collect(isLoggedIn, ::userState)
        collect(errorMsg, ::errorToaster)
    }
    override fun ActivityMainBinding.initialize() {
        transparentStatusBar()
        googleSignInFunc()

        currencySymbol.setOnClickListener {
            lifecycleScope.launch {
                viewModel.apply {
                    googleSignOut()
                }
            }
        }

    }

    private fun userState(isLoggedIn : Boolean){
        if(!isLoggedIn){
            binding.userName.text = "User"
        }
    }

    private fun onLoginResult(result : SignInResults){
        binding.userName.text = result.data?.username?:"User"
    }

    private fun errorToaster(e : String){
        if(e.isNotEmpty()) toastUtil(e)
    }


    private val signInLauncher = registerForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            lifecycleScope.launch {
                viewModel.getSignInWithIntent(result.data?:return@launch)
            }
        }
    }

    private fun googleSignInFunc() = binding.apply {
        userName.setOnClickListener {
            lifecycleScope.launch {
                val signInIntentSender = viewModel.requestGoogleSignIn()
                signInLauncher.launch(IntentSenderRequest.Builder(signInIntentSender ?: return@launch).build())
            }
        }
    }

}