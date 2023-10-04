package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.DatabaseRepository
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntityList
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntityList
import com.itechcom.domain.mapper.mapToFirebaseCallModel
import com.itechcom.domain.mapper.mapToIncomeExpensesEntity
import com.itechcom.domain.mapper.mapToPlanEntity
import com.itechcom.domain.mapper.mapToDataPlanEntity
import com.itechcom.domain.mapper.mapToDataUserEntity
import com.itechcom.domain.mapper.mapToListOfIncomeEntity
import com.itechcom.domain.mapper.mapToPlanEntityList
import com.itechcom.domain.model.auth.FirebaseCallModel
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.IncomeExpensesEntityList
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.model.database.UserEntity
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository,
) {
    suspend fun addPlan(planEntity: PlanEntity): FirebaseCallModel {
        return databaseRepository.addPlan(
            planEntity.mapToDataPlanEntity()
        ).mapToFirebaseCallModel()
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

    suspend fun getPlan(key : String) : FirebaseCallModel {
        val response = databaseRepository.getPlan(key)
        val data = response.data
        if(data is DataPlanEntity){
            return FirebaseCallModel(
                response.isSuccess,
                data.mapToPlanEntity(),
                response.errorMessage
            )
        }
        return response.mapToFirebaseCallModel()
    }

    suspend fun getAllIncomeAndExpenses(planId : String) : FirebaseCallModel {
        val response = databaseRepository.getAllIncomeAndExpenses(planId)
        val data = response.data
        return if(data is DataIncomeExpensesEntityList){
            FirebaseCallModel(
                response.isSuccess,
                data.mapToListOfIncomeEntity(),
                response.errorMessage )
        }else{
            response.mapToFirebaseCallModel()
        }
    }

    suspend fun updatePlan(
        key: String,
        incomeExpensesEntity: PlanEntity
    ) : FirebaseCallModel {
        val response = databaseRepository.updatePlan(key, incomeExpensesEntity.mapToDataPlanEntity())
        return response.mapToFirebaseCallModel()
    }

    suspend fun editIncomeAndExpenses(
        key: String,
        entity: IncomeExpensesEntity
    ) : FirebaseCallModel {
        val response = databaseRepository.editIncomeAndExpenses(key, entity.mapToIncomeExpensesEntity())
        return response.mapToFirebaseCallModel()
    }

    suspend fun deletePlan(key: String) : FirebaseCallModel{
        val response = databaseRepository.deletePlan(key)
        return response.mapToFirebaseCallModel()
    }


}