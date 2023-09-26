package com.itechcom.domain.mapper

import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.model.database.UserEntity

fun PlanEntity.mapToPlanEntity() : DataPlanEntity {
    return DataPlanEntity(planId, stringDate, totalIncome, totalExpenses)
}

fun UserEntity.mapToDataUserEntity() : DataUserEntity {
    return DataUserEntity(userId, name, userName, userPassword)
}

fun IncomeExpensesEntity.mapToIncomeExpensesEntity() : DataIncomeExpensesEntity {
    return DataIncomeExpensesEntity(id, planId, category, name, amount, stringDate, description)
}