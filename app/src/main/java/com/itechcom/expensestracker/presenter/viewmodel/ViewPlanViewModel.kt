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
class ViewPlanViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
): ViewModel() {

    private val _plan = MutableStateFlow(PlanEntity())
    val plan = _plan.asStateFlow()
    private val _incomeExpenses = MutableStateFlow(IncomeExpensesEntityList())
    val incomeExpenses = _incomeExpenses.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()


    suspend fun getPlan(key : String){
        val response = databaseUseCase.getPlan(key)
        if(response.isSuccess){
            val data = response.data
            if(data is PlanEntity) _plan.value = data
        }
        else _errorMessage.value = response.errorMessage?:return
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

    fun clearStates(){
        _plan.value = PlanEntity()
        _incomeExpenses.value = IncomeExpensesEntityList()
    }

}