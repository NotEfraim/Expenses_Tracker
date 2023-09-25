package com.itechcom.data.repositoryImp

import com.itechcom.data.repository.SharedPrefRepository
import com.itechcom.data.storage.sharedpref.SharedPrefManager
import javax.inject.Inject

class SharePrefRepositoryImp @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) : SharedPrefRepository {
    override fun getString(key: String) = sharedPrefManager.getString(key)

    override fun setString(key: String, value: String) = sharedPrefManager.setString(key, value)

    override fun getBoolean(key: String, value: Boolean?) = sharedPrefManager.getBoolean(key, value)

    override fun setBoolean(key: String, value: Boolean) = sharedPrefManager.setBoolean(key, value)

    override fun getInt(key: String) = sharedPrefManager.getInt(key)

    override fun setInt(key: String, value: Int) = sharedPrefManager.setInt(key, value)

}