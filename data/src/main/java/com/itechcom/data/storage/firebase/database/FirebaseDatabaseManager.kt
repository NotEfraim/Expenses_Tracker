package com.itechcom.data.storage.firebase.database

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class FirebaseDatabaseManager {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val usersTable = firebaseDatabase.getReference(NODE_USERS)
    private val plansTable = firebaseDatabase.getReference(NODE_PLAN)
    private val incomeExpensesTable = firebaseDatabase.getReference(NODE_INCOME_EXPENSES)

    suspend fun addPlan(planEntity: DataPlanEntity) : DataFirebaseCallModel {
        return try {
            planEntity.planId = plansTable.push().key
            planEntity.planId?.let {
                plansTable.child(it).setValue(planEntity).await()
            }
            DataFirebaseCallModel(true, "")
        }catch (e : Exception){
            if (e is CancellationException) throw e
            DataFirebaseCallModel(false, "${e.message}")
        }
    }

    suspend fun addUser(userEntity: DataUserEntity) : DataFirebaseCallModel {
        return try {
            userEntity.userId = usersTable.push().key
            userEntity.userId?.let {
                usersTable.child(it).setValue(userEntity).await()
            }
            DataFirebaseCallModel(true, "")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, "${e.message}")
        }
    }
    suspend fun addIncomeExpenses(incomeExpensesEntity: DataIncomeExpensesEntity) : DataFirebaseCallModel {
        return try {
            incomeExpensesEntity.id = incomeExpensesTable.push().key
            incomeExpensesEntity.id?.let {
                incomeExpensesTable.child(it).setValue(incomeExpensesEntity).await()
            }
            DataFirebaseCallModel(true, "")
        }catch (e : Exception){
            if (e is CancellationException) throw e
            DataFirebaseCallModel(false, "${e.message}")
        }
    }

    suspend fun getBasicAuthUser(email : String) = flow {
        try {

            val query = usersTable.orderByChild("userName")
                .equalTo(email)
                .limitToFirst(1)
                .get()
                .await()

            if(query.exists()){
                val userResponse = query.children.first().getValue(DataUserEntity::class.java)
                emit(DataFirebaseCallModel(true, userResponse, "" ))
            }

            else emit(DataFirebaseCallModel(false, "" ))

        }catch (e : Exception){
            if(e is CancellationException) throw e
            emit(DataFirebaseCallModel(false, "${e.message}"))
        }
    }

}