package com.itechcom.data.storage.firebase.database

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntityList
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
                val callResponse = query.children.first().getValue(DataUserEntity::class.java)
                emit(DataFirebaseCallModel(true, callResponse, "" ))
            }

            else emit(DataFirebaseCallModel(false, "" ))

        }catch (e : Exception){
            if(e is CancellationException) throw e
            emit(DataFirebaseCallModel(false, "${e.message}"))
        }
    }

    suspend fun getAllPlans(limitTo : Int) : DataFirebaseCallModel {
        return try {
            val query = plansTable.limitToFirst(limitTo).get().await()

            if(query.exists()){
                val callResponse = query.getValue(DataPlanEntityList::class.java)
                val list = arrayListOf<DataPlanEntity>()
                query.children.map {
                    val plan = it.getValue(DataPlanEntity::class.java)
                    plan?.let { list.add(plan) }
                }
                callResponse?.data = list.toList()
                DataFirebaseCallModel(true, callResponse, "")
            }
            else DataFirebaseCallModel(false, "")

        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, "${e.message}")
        }
    }


}