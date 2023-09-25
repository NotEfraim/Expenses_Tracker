package com.itechcom.data.repository

interface SharedPrefRepository {
    fun getString(key : String) : String?

    fun setString(key : String, value : String)

    fun getBoolean(key: String, value : Boolean? = null) : Boolean

    fun setBoolean(key : String, value : Boolean)

    fun getInt(key: String) : Int

    fun setInt(key : String, value: Int)
}