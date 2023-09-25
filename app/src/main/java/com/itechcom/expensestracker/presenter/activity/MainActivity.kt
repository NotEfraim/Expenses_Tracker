package com.itechcom.expensestracker.presenter.activity

import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.SignInResults
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityMainBinding
import com.itechcom.expensestracker.presenter.viewmodel.LoginRegisterViewModel
import com.itechcom.expensestracker.utils.extensions.collect
import com.itechcom.expensestracker.utils.extensions.transparentStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, LoginRegisterViewModel>(
    ActivityMainBinding::inflate,
    LoginRegisterViewModel::class)
{

    override fun LoginRegisterViewModel.initCall() {
        lifecycleScope.launch {
            isAlreadySignedIn()
        }
    }

    override fun LoginRegisterViewModel.initObserver() {
        collect(state, ::initUser)
    }

    override fun ActivityMainBinding.initialize() {
        transparentStatusBar()
    }

    private fun initUser(data : SignInResults){
        binding.userName.text = data.data?.username
    }


}