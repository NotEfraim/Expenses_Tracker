package com.itechcom.domain.mapper

import com.itechcom.data.model.DataSignInResults
import com.itechcom.data.model.DataUserData
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntityList
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntityList
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
import com.itechcom.domain.model.auth.SignInResults
import com.itechcom.domain.model.auth.UserData
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.IncomeExpensesEntityList
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.model.database.PlanEntityList
import com.itechcom.domain.model.database.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


suspend fun Flow<DataUserData?>.userDataMap() : Flow<UserData> = flow {
    val model = UserData()
    this@userDataMap.collect {
        it?.run {
            model.userId = it.userId
            model.username = it.username
            model.profilePictureUrl = it.profilePictureUrl
        }
    }
    emit(model)
}

suspend fun Flow<DataSignInResults?>.signInResultsMap() = flow {
    val model = SignInResults()
    this@signInResultsMap.collect {
        it?.run {
            model.data = UserData(
                it.data?.userId,
                it.data?.username,
                it.data?.profilePictureUrl
            )
            model.errorMsg = it.errorMsg
        }
    }
    emit(model)
}

fun DataPlanEntityList.mapToPlanEntityList() : PlanEntityList {
    return PlanEntityList(
        data = this@mapToPlanEntityList.data?.mapToListPlanEntity()
    )
}

fun List<DataPlanEntity>.mapToListPlanEntity() : List<PlanEntity>{
    val model : ArrayList<PlanEntity> = arrayListOf()
    this.map {
        model.add(PlanEntity(
            planId = it.planId,
            stringDate = it.stringDate,
            userName = it.userName,
            budget = it.budget,
            totalIncome = it.totalIncome,
            totalExpenses = it.totalExpenses,
            description = it.description
        ))
    }
    return model.toList()
}

fun DataPlanEntity.mapToPlanEntity() : PlanEntity {
    return PlanEntity(
        planId,
        userName,
        stringDate,
        budget,
        totalIncome,
        totalExpenses,
        description
    )
}

fun DataUserEntity.mapToUserDataEntity() : UserEntity {
    return UserEntity(userId, name, userName, userPassword)
}

fun DataIncomeExpensesEntityList.mapToListOfIncomeEntity() : IncomeExpensesEntityList {
    val list : ArrayList<IncomeExpensesEntity> = arrayListOf()
    this.data?.map {
        list.add(
            IncomeExpensesEntity(
            planId = it.planId,
            type = it.type,
            name = it.name,
            amount = it.amount,
            stringDate = it.stringDate,
            description = it.description
        )
        )
    }
    return IncomeExpensesEntityList(data = list.toList())
}

fun DataIncomeExpensesEntity.mapToDataIncomeExpensesEntity() : IncomeExpensesEntity {
    return IncomeExpensesEntity(id, planId, type, name, amount, stringDate, description)
}