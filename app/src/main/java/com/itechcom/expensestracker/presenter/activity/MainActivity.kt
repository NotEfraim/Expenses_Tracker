package com.itechcom.expensestracker.presenter.activity

import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.SignInResults
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityMainBinding
import com.itechcom.expensestracker.presenter.viewmodel.LoginViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.transparentStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, LoginViewModel>(
    ActivityMainBinding::inflate,
    LoginViewModel::class)
{

    override fun LoginViewModel.initCall() {
        lifecycleScope.launch {
            isAlreadySignedIn()
        }
    }

    override fun LoginViewModel.initObserver() {
        collect(state, ::initUser)
    }

    override fun ActivityMainBinding.initialize() {
        transparentStatusBar()
    }

    private fun initUser(data : SignInResults){
        binding.userName.text = data.data?.username?:"Maybe a user"
    }


}