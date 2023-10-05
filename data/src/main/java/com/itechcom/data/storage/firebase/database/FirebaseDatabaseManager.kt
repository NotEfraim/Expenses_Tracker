package com.itechcom.data.storage.firebase.database

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.itechcom.data.model.DataFirebaseCallModel
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntityList
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntityList
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
import com.itechcom.data.storage.sharedpref.SharedPrefManager
import com.itechcom.data.util.toMap
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class FirebaseDatabaseManager @Inject constructor(
    sharedPrefManager: SharedPrefManager
) {

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val usersTable = firebaseDatabase.getReference(NODE_USERS)
    private val plansTable = firebaseDatabase.getReference(NODE_PLAN)
    private val incomeExpensesTable = firebaseDatabase.getReference(NODE_INCOME_EXPENSES)
    private val userEmail by lazy {  sharedPrefManager.getString("PREF_EMAIL") }

    suspend fun addPlan(planEntity: DataPlanEntity) : DataFirebaseCallModel {
        return try {
            planEntity.planId = plansTable.push().key
            planEntity.userName = userEmail
            planEntity.planId?.let {
                plansTable.child(it).setValue(planEntity).await()
            }
            DataFirebaseCallModel(true, null, "")
        }catch (e : Exception){
            if (e is CancellationException) throw e
            DataFirebaseCallModel(false, null,"${e.message}")
        }
    }

    suspend fun addUser(userEntity: DataUserEntity) : DataFirebaseCallModel {
        return try {
            userEntity.userId = usersTable.push().key
            userEntity.userId?.let {
                usersTable.child(it).setValue(userEntity).await()
            }
            DataFirebaseCallModel(true, null,"")
        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, null,"${e.message}")
        }
    }
    suspend fun addIncomeExpenses(incomeExpensesEntity: DataIncomeExpensesEntity) : DataFirebaseCallModel {
        return try {
            incomeExpensesEntity.id = incomeExpensesTable.push().key
            incomeExpensesEntity.id?.let {
                incomeExpensesTable.child(it).setValue(incomeExpensesEntity).await()
            }
            DataFirebaseCallModel(true, null,"")
        }catch (e : Exception){
            if (e is CancellationException) throw e
            DataFirebaseCallModel(false, null,"${e.message}")
        }
    }

    suspend fun getBasicAuthUser(email : String) = flow {
        try {

            val query = usersTable.orderByChild("userName")
                .equalTo(userEmail)
                .get()
                .await()

            if(query.exists()){
                val callResponse = query.children.first().getValue(DataUserEntity::class.java)
                emit(DataFirebaseCallModel(true, callResponse, "" ))
            }
            else emit(DataFirebaseCallModel(false, null,"" ))

        }catch (e : Exception){
            if(e is CancellationException) throw e
            emit(DataFirebaseCallModel(false, "${e.message}"))
        }
    }

    suspend fun getAllPlans(limitTo : Int) : DataFirebaseCallModel {
        return try {
            val query = plansTable.limitToFirst(limitTo)
                .orderByChild("userName")
                .equalTo(userEmail)
                .get()
                .await()

            if(query.exists()){
                val callResponse = query.getValue(DataPlanEntityList::class.java)
                val list = arrayListOf<DataPlanEntity>()
                query.children.map {
                    val plan = it.getValue(DataPlanEntity::class.java)
                    plan?.planId = it.key
                    plan?.let { list.add(plan) }
                }
                callResponse?.data = list.toList().reversed()
                DataFirebaseCallModel(true, callResponse, "")
            }
            else DataFirebaseCallModel(false, null,"")

        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, "${e.message}")
        }
    }


    suspend fun getPlan(key : String) : DataFirebaseCallModel {
        return try {
            val query = plansTable.child(key).get().await()

            if(query.exists()){
                val data = query.getValue(DataPlanEntity::class.java)
                DataFirebaseCallModel(true, data, null)
            }
            else DataFirebaseCallModel(false, null, null)

        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, null, e.message)
        }
    }

    suspend fun getLatestPlan() : DataFirebaseCallModel {
        return try {
            val query = plansTable.orderByChild("userName")
                .limitToLast(1)
                .equalTo(userEmail)
                .get()
                .await()

            if(query.exists()){
                val response = query.children.first().getValue(DataPlanEntity::class.java)
                DataFirebaseCallModel(true, response, "")
            }
            else DataFirebaseCallModel(false, "")

        }catch (e: Exception){
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, "${e.message}")
        }
    }

    suspend fun getAllIncomeAndExpenses(planId : String) : DataFirebaseCallModel {
        return try {
            val query = incomeExpensesTable
                .orderByChild("planId")
                .equalTo(planId)
                .get()
                .await()

            if(query.exists()){
                val list = arrayListOf<DataIncomeExpensesEntity>()
                query.children.map {
                    val s = it.getValue(DataIncomeExpensesEntity::class.java)
                    s?.let { d -> list.add(d) }
                }
                DataFirebaseCallModel(true, DataIncomeExpensesEntityList(data = list), "")

            }
            else DataFirebaseCallModel(true, null, "")

        }catch (e : Exception){
            Log.d("queryMe", "${e.message}")
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, null, "${e.message}")
        }
    }

    suspend fun updatePlan(key : String, entity: DataPlanEntity) : DataFirebaseCallModel {
        val updateData = entity.toMap()
        val query = plansTable.child(key).updateChildren(updateData)
        query.await()
        return try {
            Log.d("updateBEBE", "updatePlan ${query}")
                DataFirebaseCallModel(true, null, null)
        }catch (e : Exception){
            Log.d("updateBEBE", "updatePlan ${e.message}")
            DataFirebaseCallModel(false, null, e.message)
        }
    }

    suspend fun editIncomeAndExpenses(key : String, entity : DataIncomeExpensesEntity) : DataFirebaseCallModel {
        val updateData = entity.toMap()
        return try {
            val query = incomeExpensesTable.child(key).updateChildren(updateData)
            query.await()
            Log.d("updateBEBE", "editIncomeAndExpenses ${query}")
            DataFirebaseCallModel(true, null, null)
        }catch (e : Exception){
            Log.d("updateBEBE", "editIncomeAndExpenses ${e.message}")
            DataFirebaseCallModel(false, null, e.message)
        }
    }

    suspend fun deletePlan(key: String) : DataFirebaseCallModel {
        return try {
            plansTable.child(key).removeValue().await()
            deleteAllIncomeExpensesByKey(key)
        }catch (e : Exception){
            DataFirebaseCallModel(false, null, e.message)
        }
    }

    private suspend fun deleteAllIncomeExpensesByKey(planId: String) : DataFirebaseCallModel{
        return try {
            val query = incomeExpensesTable
                .orderByChild("planId")
                .equalTo(planId)
                .get()
                .await()
            if(query.exists()){
                query.children.map {
                    incomeExpensesTable.child(it.key?:return@map ).removeValue().await()
                }
                DataFirebaseCallModel(true, null, null)
            }
            else DataFirebaseCallModel(true, null, "")

        }catch (e : Exception){
            if(e is CancellationException) throw e
            DataFirebaseCallModel(false, null, "${e.message}")
        }
    }


}