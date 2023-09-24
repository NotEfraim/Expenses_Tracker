package com.itechcom.data.storage.firebase.database

import com.google.firebase.database.FirebaseDatabase
import com.itechcom.data.storage.firebase.database.entity.IncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.PlanEntity
import com.itechcom.data.storage.firebase.database.entity.UserEntity

class FirebaseDatabaseManager {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val usersTable = firebaseDatabase.getReference(NODE_USERS)
    private val plansTable = firebaseDatabase.getReference(NODE_PLAN)
    private val incomeExpensesTable = firebaseDatabase.getReference(NODE_INCOME_EXPENSES)

    suspend fun addPlan(planEntity: PlanEntity){
        planEntity.planId = plansTable.push().key
        planEntity.planId?.let {
            plansTable.child(it).setValue(planEntity)
        }
    }

    suspend fun addUser(userEntity: UserEntity){
        userEntity.userId = usersTable.push().key
        userEntity.userId?.let {
            usersTable.child(it).setValue(userEntity)
        }

    }

    suspend fun addIncomeExpenses(incomeExpensesEntity: IncomeExpensesEntity){
        incomeExpensesEntity.id = incomeExpensesTable.push().key
        incomeExpensesEntity.id?.let {
            incomeExpensesTable.child(it).setValue(incomeExpensesEntity)
        }
    }

}