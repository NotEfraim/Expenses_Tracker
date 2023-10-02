package com.itechcom.data.repositoryImp.firebase

import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.data.repository.firebase.DatabaseRepository
import com.itechcom.data.storage.firebase.database.FirebaseDatabaseManager
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntityList
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class DatabaseRepositoryImp @Inject constructor(
    private val firebaseDatabaseManager: FirebaseDatabaseManager
) : DatabaseRepository {
    override suspend fun addPlan(planEntity: DataPlanEntity) =
        firebaseDatabaseManager.addPlan(planEntity)

    override suspend fun addUser(userEntity: DataUserEntity) =
        firebaseDatabaseManager.addUser(userEntity)

    override suspend fun addIncomeAndExpenses(incomeExpensesEntity: DataIncomeExpensesEntity)
    = firebaseDatabaseManager.addIncomeExpenses(incomeExpensesEntity)

    override suspend fun getBasicAuthUser(email: String) =
        firebaseDatabaseManager.getBasicAuthUser(email)

    override suspend fun getAllPlans(limitTo: Int) = firebaseDatabaseManager.getAllPlans(limitTo)

    override suspend fun getLatestPlan(): DataFirebaseCallModel = firebaseDatabaseManager.getLatestPlan()
    override suspend fun getPlan(key: String) = firebaseDatabaseManager.getPlan(key)
    override suspend fun getAllIncomeAndExpenses(planId: String)
    = firebaseDatabaseManager.getAllIncomeAndExpenses(planId)

}