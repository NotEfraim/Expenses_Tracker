package com.itechcom.data.repositoryImp

import com.itechcom.data.repository.SharedPrefRepository
import com.itechcom.data.storage.sharedpref.SharedPrefManager
import javax.inject.Inject

class SharePrefRepositoryImp @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) : SharedPrefRepository {
    override fun setValue(key : String, any: Any) = sharedPrefManager.setValue(key, any)

    override fun getValue(key : String, type : Any): Any? = sharedPrefManager.getValue(key, type)
}