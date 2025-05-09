package com.example.orderterminal.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
