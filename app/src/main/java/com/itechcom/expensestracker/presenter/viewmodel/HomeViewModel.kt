package com.itechcom.expensestracker.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
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
    private val _errorToast = MutableStateFlow("")
    val errorToast = _errorToast.asStateFlow()

    suspend fun getAllPlans(limitTo : Int){
        Log.d("testCall", "boom: ")
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
}