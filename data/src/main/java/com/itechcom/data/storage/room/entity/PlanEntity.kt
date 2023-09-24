package com.itechcom.data.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan_table")
class PlanEntity(
    @PrimaryKey(autoGenerate = true)
    val planId : Int? = null,
    @ColumnInfo(name = "stringDate")
    val stringDate : String? = null,
    @ColumnInfo(name = "totalIncome")
    val totalIncome : String? = null,
    @ColumnInfo(name = "totalExpenses")
    val totalExpenses : String? = null
)