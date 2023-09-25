package com.itechcom.expensestracker.utils.extensions

import androidx.fragment.app.FragmentManager
import com.itechcom.expensestracker.presenter.dialog.LoadingDialog

val loadingDialog by lazy { LoadingDialog() }

fun FragmentManager.showLoadingDialog(){
    loadingDialog.show(this, "loading")
}

fun FragmentManager.hideLoadingDialog(){
    loadingDialog.dismiss()
    this.beginTransaction().remove(loadingDialog).commit()
}