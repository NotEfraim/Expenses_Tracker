package com.itechcom.data.storage.sharedpref

import android.content.Context
import javax.inject.Inject

class SharedPrefManager @Inject constructor(
    val context: Context
) {

    private val sharedPrefManager = context.getSharedPreferences("LocalPref", Context.MODE_PRIVATE)

    fun setValue(key: String, value: Any) {
        when(value){
            is String ->{
                setString(key, value)
            }
            is Int -> {
                setInt(key, value)
            }
            is Boolean -> {
                setBoolean(key, value)
            }
        }
    }

    fun getValue(key: String, type : Any) : Any? {
        when(type){
            is String ->{
                return getString(key)
            }
            is Int -> {
                return getInt(key)
            }
            is Boolean -> {
                return getBoolean(key)
            }
        }
        return null
    }

    private fun getString(key : String) = sharedPrefManager.getString(key, "")

    private fun setString(key : String, value : String) = sharedPrefManager.edit().putString(key, value).apply()

    private fun getBoolean(key: String, value : Boolean? = null) = sharedPrefManager.getBoolean(key, value?:false)

    private fun setBoolean(key : String, value : Boolean) = sharedPrefManager.edit().putBoolean(key, value).apply()

    private fun getInt(key: String) = sharedPrefManager.getInt(key, 0)

    private fun setInt(key : String, value: Int) = sharedPrefManager.edit().putInt(key, value).apply()
}

