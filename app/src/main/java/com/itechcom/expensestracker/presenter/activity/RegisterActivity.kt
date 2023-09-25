package com.itechcom.expensestracker.presenter.activity

import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityRegisterBinding
import com.itechcom.expensestracker.presenter.viewmodel.LoginRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding, LoginRegisterViewModel>(
    ActivityRegisterBinding::inflate,
    LoginRegisterViewModel::class) {

    override fun ActivityRegisterBinding.initialize() {
        initView()
    }

    private fun initView() = binding.apply {
        haveAnAccountBtn.setOnClickListener { finish() }
    }
}