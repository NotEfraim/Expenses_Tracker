package com.itechcom.expensestracker.utils.extensions

import android.app.Activity
import android.app.Dialog
import androidx.fragment.app.Fragment
import com.itechcom.expensestracker.presenter.dialog.LoadingDialog


fun Activity.createLoadingDialog(): Dialog {
    return LoadingDialog(this).create()
}

fun Fragment.createLoadingDialog(): Dialog {
    return LoadingDialog(requireContext()).create()
}
