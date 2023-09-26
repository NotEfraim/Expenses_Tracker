package com.itechcom.domain.usecase

import com.itechcom.data.repository.firebase.DatabaseRepository
import com.itechcom.domain.mapper.mapToFirebaseCallModel
import com.itechcom.domain.mapper.mapToIncomeExpensesEntity
import com.itechcom.domain.mapper.mapToPlanEntity
import com.itechcom.domain.mapper.mapToDataUserEntity
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.model.database.UserEntity
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend fun addPlan(planEntity: PlanEntity) = databaseRepository.addPlan(
        planEntity.mapToPlanEntity()
    ).mapToFirebaseCallModel()

    suspend fun addIncomeExpensesEntity(incomeExpensesEntity: IncomeExpensesEntity) =
        databaseRepository.addIncomeAndExpenses(
            incomeExpensesEntity.mapToIncomeExpensesEntity()
        ).mapToFirebaseCallModel()

    suspend fun addUser(userEntity: UserEntity) =
        databaseRepository.addUser(
            userEntity.mapToDataUserEntity()
        ).mapToFirebaseCallModel()

}