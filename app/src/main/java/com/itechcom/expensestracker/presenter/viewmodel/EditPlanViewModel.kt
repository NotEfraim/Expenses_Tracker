package com.itechcom.expensestracker.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditPlanViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    suspend fun updatePlan(key : String, entity: PlanEntity) : Boolean{
        val response = databaseUseCase.updatePlan(key, entity)
        return response.isSuccess
    }

}