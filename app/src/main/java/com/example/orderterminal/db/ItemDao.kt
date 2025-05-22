package com.example.orderterminal.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("select * from items order by code asc")
    fun getAll(): Flow<List<Item>>

    @Query("select * from items where items.code==:code ")
    fun getItemByCode(code:String): Flow<Item>

    @Insert
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)
}