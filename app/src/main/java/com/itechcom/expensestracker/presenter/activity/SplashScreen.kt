package com.itechcom.expensestracker.presenter.activity

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.data.sharedpref.SharedPrefManager
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
        viewModel.sharedPrefManager.let { pref ->
            val isFreshInstall = pref.getBoolean(SharedPrefKey.isFreshInstall, true)
            if(isFreshInstall)
                initCounter(pref)
            else {
                intentTo<MainActivity> {  }
                finish()
            }
        }
    }

    private fun initCounter(pref: SharedPrefManager){
        lifecycleScope.launch {
            pref.setBoolean(SharedPrefKey.isFreshInstall, false)
            delay(5000)
            intentTo<MainActivity> {  }
            finish()
        }
    }

}