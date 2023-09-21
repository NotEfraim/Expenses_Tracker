package com.itechcom.expensestracker.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses_table")
class ExpensesEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null,
    @ColumnInfo(name = "planId")
    val planId : Int? = null,
    @ColumnInfo(name = "name")
    val name : String? = null,
    @ColumnInfo(name = "amount")
    val amount : Int? = null,
    @ColumnInfo(name = "stringDate")
    val stringDate : String? = null,
    @ColumnInfo(name = "description")
    val description : String? = null
)