package com.itechcom.domain.model.database

class PlanEntity(
    var planId : String? = null,
    var stringDate : String? = null,
    var budget : String? = null,
    var totalIncome : String? = null,
    var totalExpenses : String? = null,
    var description : String? = null
)

data class DataPlanEntityList(
    val data : List<PlanEntity> = arrayListOf()
)