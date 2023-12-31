package com.itechcom.expensestracker.presenter.activity

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityMainBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.utils.Constants
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.intentTo
import com.itechcom.expensestracker.utils.extensions.toastUtil
import com.itechcom.expensestracker.utils.extensions.transparentStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate,
    MainViewModel::class)
{

    override fun MainViewModel.initCall() {
        lifecycleScope.launch {
        }
    }

    override fun MainViewModel.initObserver() {
        collect(userName, ::initUser)
    }

    override fun ActivityMainBinding.initialize() {
        transparentStatusBar()
        getUserName()

        logoutBtn.setOnClickListener {
            showLoadingDialog()
            lifecycleScope.launch {
                val response = viewModel.logoutUser {}
                if(response){
                    intentTo<LoginActivity> {  }
                    finish()
                }else{
                    toastUtil("Unknown Error Occur!")
                }
                hideLoadingDialog()
            }
        }
    }

    private fun getUserName() = viewModel.apply {
        lifecycleScope.launch {
            val intentEmail = getSharedPrefEmail(Constants.PREF_EMAIL)
            val loginType = getLoginType(Constants.LOGIN_TYPE)
            if(intentEmail != null){
                getSignInUser(loginType,intentEmail)
            }
        }
    }

    private fun initUser(userName : String?){
        binding.userName.text = userName
    }


}