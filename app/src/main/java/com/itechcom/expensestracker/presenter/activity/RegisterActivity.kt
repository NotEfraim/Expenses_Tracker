package com.itechcom.expensestracker.presenter.activity

import androidx.lifecycle.lifecycleScope
import com.itechcom.domain.model.database.UserEntity
import com.itechcom.expensestracker.base.BaseActivity
import com.itechcom.expensestracker.databinding.ActivityRegisterBinding
import com.itechcom.expensestracker.presenter.viewmodel.RegisterViewModel
import com.itechcom.expensestracker.utils.Constants
import com.itechcom.expensestracker.utils.extensions.intentTo
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
        registerBtn.setOnClickListener {
            showLoadingDialog()
            registerWithEmailAndPassword()
        }
    }

    private fun registerWithEmailAndPassword() = binding.apply {
        val name =  name.getFieldText()
        val email = email.getFieldText()
        val password = password.getFieldText()
        val confirmPassword = confirmPassword.getFieldText()

        if(confirmPassword == password){
            authenticateUser(name, email, password)
        }else {
            toastUtil("Password not match.")
            hideLoadingDialog()
        }
    }

    private fun authenticateUser(name : String, email : String, password : String) = viewModel.apply {
        lifecycleScope.launch {
            val registerResult = registerWithEmailAndPassword(email, password)
            if(registerResult.isSuccess){
                val res = saveToDatabase(UserEntity(
                    name = name,
                    userName = email,
                    userPassword = password
                ))
                if(res){
                    saveEmailToPref(Constants.prefEmail, email)
                    hideLoadingDialog()
                    delay(1000)
                    toastUtil("Registered Successfully")
                    finish()
                }
            }else  toastUtil(registerResult.errorMessage?:"")
        }
    }

    private suspend fun saveToDatabase(userEntity: UserEntity) : Boolean {
        val result = viewModel.addUser(userEntity)
        if(!result.isSuccess) toastUtil(result.errorMessage?:"")
        return result.isSuccess
    }

}