package com.itechcom.data.storage.firebase.database.entity

import com.google.firebase.database.Exclude

class PlanEntity(
    @get:Exclude
    var planId : String? = null,
    var stringDate : String? = null,
    var totalIncome : String? = null,
    var totalExpenses : String? = null
)