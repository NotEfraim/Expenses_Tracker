package com.itechcom.domain.mapper

import com.itechcom.data.model.DataSignInResults
import com.itechcom.data.model.DataUserData
import com.itechcom.data.storage.firebase.database.entity.DataIncomeExpensesEntity
import com.itechcom.data.storage.firebase.database.entity.DataPlanEntity
import com.itechcom.data.storage.firebase.database.entity.DataUserEntity
import com.itechcom.domain.model.auth.SignInResults
import com.itechcom.domain.model.auth.UserData
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.PlanEntity
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

fun DataPlanEntity.mapToPlanEntity() : PlanEntity {
    return PlanEntity(planId, stringDate, totalIncome, totalExpenses)
}

fun DataUserEntity.mapToUserDataEntity() : UserEntity {
    return UserEntity(userId, name, userName, userPassword)
}

fun DataIncomeExpensesEntity.mapToDataIncomeExpensesEntity() : IncomeExpensesEntity{
    return IncomeExpensesEntity(id, planId, category, name, amount, stringDate, description)
}