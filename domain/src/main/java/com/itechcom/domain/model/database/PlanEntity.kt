package com.itechcom.domain.model.database

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlanEntity(
    @SerializedName("planId")
    var planId : String? = null,
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

data class PlanEntityList(
    @SerializedName("data")
    var data : List<PlanEntity>? = null
) : Serializable