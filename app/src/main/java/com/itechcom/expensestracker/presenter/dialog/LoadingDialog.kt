package com.itechcom.expensestracker.presenter.dialog

import android.view.View
import androidx.fragment.app.FragmentManager
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseDialog

class LoadingDialog : BaseDialog(R.layout.dialog_loading) {
    override fun initView(view: View?) {}
    fun showLoadingDialog(supportFragmentManager: FragmentManager, tag : String){
        show(supportFragmentManager, tag)
    }
}