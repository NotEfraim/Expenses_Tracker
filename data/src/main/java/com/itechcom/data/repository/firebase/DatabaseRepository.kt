package com.itechcom.data.repository.firebase

import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun addPlan(planEntity: DataPlanEntity) : DataFirebaseCallModel
    suspend fun addUser(userEntity: DataUserEntity) : DataFirebaseCallModel
    suspend fun addIncomeAndExpenses(incomeExpensesEntity: DataIncomeExpensesEntity) : DataFirebaseCallModel
    suspend fun getBasicAuthUser(email : String) : Flow<DataFirebaseCallModel>
    suspend fun getAllPlans(limitTo : Int) : DataFirebaseCallModel
    suspend fun getLatestPlan() : DataFirebaseCallModel
    suspend fun getPlan(key : String) : DataFirebaseCallModel
    suspend fun getAllIncomeAndExpenses(planId : String) : DataFirebaseCallModel

}