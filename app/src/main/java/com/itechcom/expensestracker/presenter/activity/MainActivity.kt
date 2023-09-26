package com.itechcom.expensestracker.presenter.activity

import androidx.lifecycle.lifecycleScope
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityMainBinding
import com.itechcom.expensestracker.presenter.viewmodel.MainViewModel
import com.itechcom.expensestracker.utils.Constants
import com.itechcom.expensestracker.utils.extensions.collect
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
    }

    private fun getUserName() = viewModel.apply {
        lifecycleScope.launch {
            val intentEmail = getSharedPrefEmail(Constants.prefEmail)
            if(intentEmail != null){
                getBasicAuthUserName(intentEmail)
            }
        }
    }

    private fun initUser(userName : String?){
        binding.userName.text = userName
    }


}