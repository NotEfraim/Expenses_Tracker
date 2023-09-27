package com.itechcom.data.storage.firebase.database.entity

import com.google.firebase.database.Exclude

data class DataPlanEntity(
    @get:Exclude
    var planId : String? = null,
    var stringDate : String? = null,
    var budget : String? = null,
    var totalIncome : String? = null,
    var totalExpenses : String? = null,
    var description : String? = null
)

data class DataPlanEntityList(
    val data : List<DataPlanEntity> = arrayListOf()
)
