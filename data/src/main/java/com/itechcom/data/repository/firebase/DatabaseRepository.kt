package com.itechcom.data.repository.firebase

import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity

interface DatabaseRepository {
    suspend fun addPlan(planEntity: DataPlanEntity) : DataFirebaseCallModel
    suspend fun addUser(userEntity: DataUserEntity) : DataFirebaseCallModel
    suspend fun addIncomeAndExpenses(incomeExpensesEntity: DataIncomeExpensesEntity) : DataFirebaseCallModel

}