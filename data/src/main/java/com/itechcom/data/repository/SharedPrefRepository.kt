package com.itechcom.data.repository

interface SharedPrefRepository {
    fun setValue(key : String, any: Any)
    fun getValue(key: String, type : Any) : Any?
}