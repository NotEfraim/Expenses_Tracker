package com.itechcom.domain.usecase

import android.util.Log
import com.itechcom.data.repository.firebase.DatabaseRepository
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntityList
import com.itechcom.domain.mapper.mapToFirebaseCallModel
import com.itechcom.domain.mapper.mapToIncomeExpensesEntity
import com.itechcom.domain.mapper.mapToPlanEntity
import com.itechcom.domain.mapper.mapToDataPlanEntity
import com.itechcom.domain.mapper.mapToDataUserEntity
import com.itechcom.domain.mapper.mapToPlanEntityList
import com.itechcom.domain.model.auth.FirebaseCallModel
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.model.database.UserEntity
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend fun addPlan(planEntity: PlanEntity) : FirebaseCallModel {
        val response = databaseRepository.addPlan(
            planEntity.mapToDataPlanEntity()
        ).mapToFirebaseCallModel()
        val planResponseEntity = response.data as DataPlanEntity?
        response.data = planResponseEntity?.mapToPlanEntity()
        return response
    }

    suspend fun addIncomeExpensesEntity(incomeExpensesEntity: IncomeExpensesEntity) =
        databaseRepository.addIncomeAndExpenses(
            incomeExpensesEntity.mapToIncomeExpensesEntity()
        ).mapToFirebaseCallModel()

    suspend fun addUser(userEntity: UserEntity) =
        databaseRepository.addUser(
            userEntity.mapToDataUserEntity()
        ).mapToFirebaseCallModel()

    suspend fun getAllPlans(limitTo: Int) : FirebaseCallModel {
        val response = databaseRepository.getAllPlans(limitTo)
        val a = response.data
        if(a is DataPlanEntityList) {
            return FirebaseCallModel(
                response.isSuccess,
                a.mapToPlanEntityList(),
                response.errorMessage
            )
        }
        return response.mapToFirebaseCallModel()
    }

    suspend fun getLatestPlan() : FirebaseCallModel {
        val response = databaseRepository.getLatestPlan()
        val a = response.data
        if(a is DataPlanEntity){
            return FirebaseCallModel(
                response.isSuccess,
                a.mapToPlanEntity(),
                response.errorMessage
            )
        }
        return response.mapToFirebaseCallModel()
    }


}