package com.itechcom.expensestracker.utils.extensions

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.chad.library.adapter.base.BaseQuickAdapter
import com.itechcom.expensestracker.R
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun Activity.transparentStatusBar() {
    window.clearFlags(
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
    )
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.TRANSPARENT
}

inline fun <reified T : Any>Context.intentTo(unit :(intent : Intent) -> Unit){
    val intent = Intent(this, T::class.java)
    unit.invoke(intent).run {
        startActivity(intent)
    }
}

fun ImageView.backFinish(){
    val context = this.context
    this.setOnClickListener { (context as Activity).finish() }
}

fun Context.toastUtil(msg : String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun BaseQuickAdapter<*, *>.useEmptyView(){
    isEmptyViewEnable = true
    setEmptyViewLayout(context, R.layout.layout_empty_view)
}

fun <T: Any>LifecycleOwner.collect(data: SharedFlow<T?>, function: (T) -> Unit) {
    this.lifecycleScope.launch {
        data.collectLatest{
            if (it != null) {
                function(it)
            }
        }
    }
}

fun View.navigateTo(into : Int, bundle: Bundle? = null){
    Navigation.findNavController(this).navigate(into, bundle)
}

fun AppCompatTextView.showDatePicker() {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(this.context, {
        _, selectedYear, selectedMonth, selectedDay ->
        this.text = formatDate(selectedYear, selectedMonth, selectedDay)

    },year, month, day)

    datePickerDialog.show()
}

private fun formatDate(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return dateFormat.format(calendar.time)
}