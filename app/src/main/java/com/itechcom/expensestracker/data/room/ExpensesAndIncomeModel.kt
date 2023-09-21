package com.itechcom.expensestracker.data.room

import com.google.gson.annotations.SerializedName


data class ExpensesAndIncomeModel(
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("planId")
    var planId : Int? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("amount")
    var amount : Int? = null,
    @SerializedName("stringDate")
    var stringDate : String? = null,
    @SerializedName("description")
    var description : String? = null
)


fun <T>singleMapper(clazz : T) : ExpensesAndIncomeModel{
    val model = ExpensesAndIncomeModel()
    clazz.let{ s ->
        when (s) {
            is ExpensesEntity -> {
                model.apply {
                    id = s.id
                    planId = s.planId
                    name = s.name
                    amount = s.amount
                    stringDate = s.stringDate
                    description = s.description
                }
            }
            is IncomeEntity -> {
                model.apply {
                    id = s.id
                    planId = s.planId
                    name = s.name
                    amount = s.amount
                    stringDate = s.stringDate
                    description = s.description
                }
            }
            else -> {}
        }
    }
    return model
}