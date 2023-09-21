package com.itechcom.expensestracker.presenter.activity

import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityMainBinding
import com.itechcom.expensestracker.presenter.SingleViewModel
import com.itechcom.expensestracker.utils.extensions.navigateTo
import com.itechcom.expensestracker.utils.extensions.transparentStatusBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, SingleViewModel>(
    ActivityMainBinding::inflate,
    SingleViewModel::class)
{

    override fun ActivityMainBinding.initialize() {
        transparentStatusBar()
    }

}