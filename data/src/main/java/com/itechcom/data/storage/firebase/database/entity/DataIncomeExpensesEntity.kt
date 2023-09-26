package com.itechcom.data.storage.firebase.database.entity

import com.google.firebase.database.Exclude

class DataIncomeExpensesEntity(
    @get:Exclude
    var id : String? = null,
    var planId : Int? = null,
    var category : String? = null,
    var name : String? = null,
    var amount : Int? = null,
    var stringDate : String? = null,
    var description : String? = null
)