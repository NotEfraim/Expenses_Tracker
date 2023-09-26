package com.itechcom.data.repositoryImp.firebase

import com.itechcom.data.repository.firebase.DatabaseRepository
import com.itechcom.data.storage.firebase.database.FirebaseDatabaseManager
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
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

}