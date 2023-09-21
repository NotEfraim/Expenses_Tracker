package com.itechcom.expensestracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
    PlanEntity::class,
    ExpensesEntity::class,
    IncomeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun applicationDao() : Dao
}