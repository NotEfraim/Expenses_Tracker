package com.itechcom.expensestracker.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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

//fun BaseQuickAdapter<*, *>.useEmptyView(){
//    isEmptyViewEnable = true
//    setEmptyViewLayout(context, R.layout.layout_empty_view)
//}

fun <T: Any>LifecycleOwner.collect(data: SharedFlow<T?>, function: (T) -> Unit) {
    this.lifecycleScope.launch {
        data.collectLatest{
            if (it != null) {
                function(it)
            }
        }
    }
}

fun View.navigateTo(into : Int){
    Navigation.findNavController(this).navigate(into)
}