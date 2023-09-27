package com.itechcom.expensestracker.presenter.viewmodel

import androidx.lifecycle.ViewModel
import com.itechcom.domain.model.auth.FirebaseCallModel
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddPlanViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    private val _addPlanResponse = MutableStateFlow(FirebaseCallModel())
    val addPlanResponse = _addPlanResponse.asStateFlow()

    suspend fun addPlan(planEntity: PlanEntity) {
        _addPlanResponse.value = databaseUseCase.addPlan(planEntity)
    }

}