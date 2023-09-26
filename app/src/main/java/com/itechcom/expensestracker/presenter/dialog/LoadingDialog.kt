package com.itechcom.expensestracker.presenter.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.itechcom.expensestracker.R
import com.itechcom.expensestracker.base.BaseDialog

class LoadingDialog(context: Context){

    private val builder = AlertDialog.Builder(context, R.style.TransparentAlertDialog)
        .setView(LayoutInflater.from(context).inflate(R.layout.dialog_loading, null))
        .setCancelable(true)
        .create()

    fun create(): AlertDialog {
        builder?.dismiss()
        return builder
    }
}