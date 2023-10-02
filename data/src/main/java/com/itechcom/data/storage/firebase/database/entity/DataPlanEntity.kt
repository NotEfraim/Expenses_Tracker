package com.itechcom.data.storage.firebase.database.entity

import com.google.firebase.database.Exclude
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataPlanEntity(
    @get:Exclude
    @SerializedName("planId")
    var planId : String? = null,
    @SerializedName("userName")
    var userName : String? = null,
    @SerializedName("stringDate")
    var stringDate : String? = null,
    @SerializedName("budget")
    var budget : String? = null,
    @SerializedName("totalIncome")
    var totalIncome : String? = null,
    @SerializedName("totalExpenses")
    var totalExpenses : String? = null,
    @SerializedName("description")
    var description : String? = null
) : Serializable

data class DataPlanEntityList(
    @SerializedName("data")
    var data : List<DataPlanEntity>? = null
) : Serializable
