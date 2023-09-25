package com.itechcom.expensestracker.presenter.activity

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivitySplashScreenBinding
import com.itechcom.expensestracker.presenter.SingleViewModel
import com.itechcom.expensestracker.utils.SharedPrefKey
import com.itechcom.expensestracker.utils.extensions.intentTo
import com.itechcom.expensestracker.utils.extensions.transparentStatusBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : BaseActivity<ActivitySplashScreenBinding, SingleViewModel>(
    ActivitySplashScreenBinding::inflate,
    SingleViewModel::class
) {

    override fun ActivitySplashScreenBinding.initialize() {
        transparentStatusBar()
        lifecycleScope.launch {
            viewModel.sharedPrefGetBoolean(
                SharedPrefKey.isFreshInstall,
                true
            ).let { freshInstalled ->

                if(freshInstalled){
                    lifecycleScope.launch {
                        viewModel.sharedPrefSetBoolean(SharedPrefKey.isFreshInstall, false)
                        delay(5000)
                        intentTo<MainActivity> {  }
                        finish()
                    }
                }
                else {
                    intentTo<MainActivity> {  }
                    finish()
                }

            }
        }
    }
}