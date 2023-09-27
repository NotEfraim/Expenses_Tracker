package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.DatabaseRepository
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.domain.mapper.mapToFirebaseCallModel
import com.itechcom.domain.mapper.mapToIncomeExpensesEntity
import com.itechcom.domain.mapper.mapToPlanEntity
import com.itechcom.domain.mapper.mapToDataPlanEntity
import com.itechcom.domain.mapper.mapToDataUserEntity
import com.itechcom.domain.mapper.mapToFirebaseCallModelFlow
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

    suspend fun getAllPlans(limitTo: Int) =
        databaseRepository.getAllPlans(limitTo).mapToFirebaseCallModelFlow()

}