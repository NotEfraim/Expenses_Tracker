package com.itechcom.expensestracker.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.IncomeExpensesEntityList
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class IncomeExpensesViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
): ViewModel() {

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()
    private val _incomeExpenses = MutableStateFlow(IncomeExpensesEntityList())
    val incomeExpenses = _incomeExpenses.asStateFlow()


    suspend fun addIncomeExpenses(incomeExpensesEntity: IncomeExpensesEntity) : Boolean {
        val response = databaseUseCase.addIncomeExpensesEntity(incomeExpensesEntity)
        return response.isSuccess
    }

    suspend fun updateIncomeExpenses(key : String, incomeExpensesEntity: IncomeExpensesEntity) : Boolean {
        val response = databaseUseCase.editIncomeAndExpenses(key, incomeExpensesEntity)
        if(response.isSuccess){
            return true
        }
        return false
    }

    suspend fun updateIncomeOrExpensesForBanner(key: String, planEntity: PlanEntity) : Boolean {
        val response = databaseUseCase.updatePlan(key, planEntity)
        if (response.isSuccess) return true
        return false
    }

    suspend fun getIncomeExpenses(planId: String){
        val response = databaseUseCase.getAllIncomeAndExpenses(planId)
        if(response.isSuccess){
            val data = response.data
            if(data is IncomeExpensesEntityList){
                _incomeExpenses.value = data
            }
        }
        else _errorMessage.value = response.errorMessage?:return
    }

}