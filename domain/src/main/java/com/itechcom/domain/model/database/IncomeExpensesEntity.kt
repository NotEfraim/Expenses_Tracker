package com.itechcom.domain.model.database

import com.google.gson.annotations.SerializedName


data class IncomeExpensesEntity(
    @SerializedName("id")
    var id : String? = null,
    @SerializedName("planId")
    var planId : String? = null,
    @SerializedName("type")
    var type : String? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("amount")
    var amount : Int? = null,
    @SerializedName("stringDate")
    var stringDate : String? = null,
    @SerializedName("description")
    var description : String? = null
)

data class IncomeExpensesEntityList(
    @SerializedName("data")
    var data : List<IncomeExpensesEntity>? = null
)

