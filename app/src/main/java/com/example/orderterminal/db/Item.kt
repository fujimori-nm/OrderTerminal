package com.example.orderterminal.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val code: String = "",
    val name: String = "",
    val cost: Int,
    val sale: Int
)