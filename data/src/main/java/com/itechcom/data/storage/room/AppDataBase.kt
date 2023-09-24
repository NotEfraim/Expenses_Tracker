package com.itechcom.data.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itechcom.data.storage.room.dao.Dao
import com.itechcom.data.storage.room.dao.UserDao
import com.itechcom.data.storage.room.entity.ExpensesEntity
import com.itechcom.data.storage.room.entity.IncomeEntity
import com.itechcom.data.storage.room.entity.PlanEntity
import com.itechcom.data.storage.room.entity.UserEntity

@Database(entities = [
    UserEntity::class,
    PlanEntity::class,
    IncomeEntity::class,
    ExpensesEntity::class]
    , version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun applicationDao() : Dao

}