package com.itechcom.data.storage.firebase.database.entity

import com.google.firebase.database.Exclude
import com.google.gson.annotations.SerializedName

class DataIncomeExpensesEntity(
    @get:Exclude
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

data class DataIncomeExpensesEntityList(
    @SerializedName("data")
    var data : List<DataIncomeExpensesEntity>? = null
)