package com.itechcom.expensestracker.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.itechcom.domain.model.database.PlanEntity
import com.itechcom.domain.model.database.PlanEntityList
import com.itechcom.domain.usecase.DatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    private val _plansResponse = MutableStateFlow(PlanEntityList())
    val plansResponse = _plansResponse.asStateFlow()
    private val _latestPlanResponse = MutableStateFlow(PlanEntity())
    val latestPlanResponse = _latestPlanResponse.asStateFlow()
    private val _errorToast = MutableStateFlow("")
    val errorToast = _errorToast.asStateFlow()

    suspend fun getAllPlans(limitTo : Int){
        val response = databaseUseCase.getAllPlans(limitTo)
        if(response.isSuccess){
            val data = response.data
            if(data is PlanEntityList){
                _plansResponse.value = data
            }
        }else{
            _errorToast.value = response.errorMessage?:return
        }
    }

    suspend fun getLatestPlan(){
        Log.d("FirebaseXX", "View Model")
        val response = databaseUseCase.getLatestPlan()
        if(response.isSuccess){
            val data = response.data
            if(data is PlanEntity){
                _latestPlanResponse.value = data
            }else{
                _errorToast.value = response.errorMessage?:return
            }
        }
    }
}