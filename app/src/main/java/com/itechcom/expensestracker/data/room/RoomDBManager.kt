package com.itechcom.expensestracker.data.room

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class RoomDBManager @Inject constructor(context: Context) {

    private val appDataBase by lazy {
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "appDataBase"
        ).build()
    }

    private val appDao = appDataBase.applicationDao()

    suspend fun insertPlan(entity: PlanEntity) = appDao.insertPlan(entity)

    suspend fun insertExpenses(entity: ExpensesEntity) = appDao.insertExpenses(entity)

    suspend fun insertIncome(entity: IncomeEntity) = appDao.insertIncome(entity)

    suspend fun getAllPlan() = appDao.getAllPlans()

    suspend fun getAllExpenses() = appDao.getAllExpenses()

    suspend fun getAllIncome() = appDao.getAllIncome()

    suspend fun getAllPlansByDate(date : String) = appDao.getPlanByDate(date)

    suspend fun getAllExpensesByDate(date: String) = appDao.getExpensesByDate(date)

    suspend fun getAllIncomeByDate(date: String) = appDao.getIncomeByDate(date)

    suspend fun getAllIncomeAndExpenses(limit : Int) = appDao.getAllExpensesAndIncome(limit)

    suspend fun getAllExpensesAndIncomeByDateAndAmount() = appDao.getAllExpensesAndIncomeByDateAndAmount()

    suspend fun getAllExpensesAndIncomeByDate() = appDao.getAllExpensesAndIncomeByDate()

    suspend fun getAllExpensesAndIncomeByAmount() = appDao.getAllExpensesAndIncomeByAmount()

    suspend fun updatePlan(entity: PlanEntity) = appDao.updatePlan(entity)

    suspend fun updateExpenses(entity: ExpensesEntity) = appDao.updateExpenses(entity)

    suspend fun updateIncome(entity: IncomeEntity) = appDao.updateIncome(entity)

    suspend fun deletePlan(entity: PlanEntity) = appDao.deletePlan(entity)

    suspend fun deleteExpenses(entity: ExpensesEntity) = appDao.deleteExpenses(entity)

    suspend fun deleteIncome(entity: IncomeEntity) = appDao.deleteIncome(entity)
}