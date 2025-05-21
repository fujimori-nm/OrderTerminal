package com.example.orderterminal.db

import android.app.Application
import android.util.Log
import androidx.room.Room

class RoomApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
