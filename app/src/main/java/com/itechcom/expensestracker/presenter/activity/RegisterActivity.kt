package com.itechcom.expensestracker.presenter.activity

import androidx.lifecycle.lifecycleScope
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityRegisterBinding
import com.itechcom.expensestracker.presenter.viewmodel.RegisterViewModel
import com.itechcom.expensestracker.utils.extensions.toastUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(
    ActivityRegisterBinding::inflate,
    RegisterViewModel::class) {

    override fun ActivityRegisterBinding.initialize() {
        initView()
    }

    private fun initView() = binding.apply {
        haveAnAccountBtn.setOnClickListener { finish() }
        registerBtn.setOnClickListener { registerWithEmailAndPassword() }
    }

    private fun registerWithEmailAndPassword() = binding.apply {
        val name =  name.getFieldText()
        val email = email.getFieldText()
        val password = password.getFieldText()
        val confirmPassword = confirmPassword.getFieldText()

        if(confirmPassword == password){
            lifecycleScope.launch {
                val result = viewModel.registerWithEmailAndPassword(email, password)
                if(result.isSuccess){
                    toastUtil("Registered Successfully")
                    delay(1000)
                    finish()
                }else{
                    toastUtil(result.errorMessage?:"")
                }
            }
        }else toastUtil("Password not match.")
    }
}