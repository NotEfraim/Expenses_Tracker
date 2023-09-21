package com.itechcom.expensestracker.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {

    /** Create */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(entity: PlanEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(entity: ExpensesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncome(entity: IncomeEntity)

    /** READ */
    @Query("SELECT * FROM plan_table")
    suspend fun getAllPlans() : List<PlanEntity>

    @Query("SELECT * FROM expenses_table UNION ALL SELECT * FROM income_table LIMIT :limit")
    suspend fun getAllExpensesAndIncome(limit : Int) : List<ExpensesEntity>

    @Query("SELECT * FROM expenses_table")
    suspend fun getAllExpenses() : List<ExpensesEntity>

    @Query("SELECT * FROM income_table")
    suspend fun getAllIncome() : List<IncomeEntity>

    @Query("SELECT * FROM expenses_table WHERE stringDate =:date")
    suspend fun getExpensesByDate(date : String) : List<ExpensesEntity>

    @Query("SELECT * FROM income_table WHERE stringDate =:date")
    suspend fun getIncomeByDate(date: String) : List<IncomeEntity>

    @Query("SELECT * FROM plan_table WHERE stringDate =:date")
    suspend fun getPlanByDate(date: String) : List<PlanEntity>

    @Query("SELECT * FROM expenses_table UNION ALL SELECT * FROM income_table ORDER BY stringDate DESC, amount DESC")
    suspend fun getAllExpensesAndIncomeByDateAndAmount() : List<IncomeEntity>

    @Query("SELECT * FROM expenses_table UNION ALL SELECT * FROM income_table ORDER BY stringDate DESC")
    suspend fun getAllExpensesAndIncomeByDate() : List<IncomeEntity>

    @Query("SELECT * FROM expenses_table UNION ALL SELECT * FROM income_table ORDER BY amount DESC")
    suspend fun getAllExpensesAndIncomeByAmount() : List<IncomeEntity>

    /** UPDATE */
    @Update
    suspend fun updatePlan(planEntity: PlanEntity)

    @Update
    suspend fun updateExpenses(expensesEntity: ExpensesEntity)

    @Update
    suspend fun updateIncome(incomeEntity: IncomeEntity)

    /** DELETE */

    @Delete
    suspend fun deletePlan(planEntity: PlanEntity)

    @Delete
    suspend fun deleteExpenses(expensesEntity: ExpensesEntity)

    @Delete
    suspend fun deleteIncome(incomeEntity: IncomeEntity)

}