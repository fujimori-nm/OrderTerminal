package com.example.orderterminal.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "items",
    // インデックスは一旦除去
    // indices = [Index(value = ["code"], unique = true)]
)
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String = "",
    val name: String = "",
    val notes: String = "",
//    val stock: Float,
//    val cost: Float,
    val sale: Int = 0,
//    @ColumnInfo(name = "weekday_sales")
//    val weekdaySales: Int,
//    @ColumnInfo(name = "holiday_sales")
//    val holidaySales: Int,
)