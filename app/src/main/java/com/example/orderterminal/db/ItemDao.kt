package com.example.orderterminal.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Query("select * from items order by code asc")
    fun getAll(): MutableList<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun post(item: Item)

    @Delete
    fun delete(item: Item)

    @Update
    fun update(item: Item)
}