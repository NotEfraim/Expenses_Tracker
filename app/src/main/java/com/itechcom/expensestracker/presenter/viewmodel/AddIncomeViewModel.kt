package com.itechcom.expensestracker.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.itechcom.domain.model.database.IncomeExpensesEntity
import com.itechcom.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddIncomeViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
): ViewModel() {

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    suspend fun addIncome(incomeExpensesEntity: IncomeExpensesEntity) : Boolean {
        val response = databaseUseCase.addIncomeExpensesEntity(incomeExpensesEntity)
        return response.isSuccess
    }

}