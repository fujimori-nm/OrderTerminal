package com.example.orderterminal.db

import android.app.Application
import android.util.Log
import androidx.room.Room

class RoomApplication : Application() {
    companion object {
        lateinit var database: AppDb
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDb::class.java,
            "items.db"
        ).build()
        applicationContext.databaseList().forEach {
            Log.d("DBLocation", "DB: ${applicationContext.getDatabasePath(it)}")
        }
    }
}
