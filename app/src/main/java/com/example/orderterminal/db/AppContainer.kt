package com.example.orderterminal.db

import android.content.Context

class AppContainer(private val context: Context) {
    val itemRepository: ItemRepository by lazy {
        ItemRepository(AppDatabase.getAppDatabase(context).itemDao())
    }
}