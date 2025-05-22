package com.example.orderterminal.db

class ItemRepository(private val itemDao: ItemDao) {

    fun gatAllItems() = itemDao.getAll()

    fun getItemByCode(code: String) = itemDao.getItemByCode(code)

    suspend fun insertItem(item: Item) = itemDao.insert(item)

    suspend fun deleteItem(item: Item) = itemDao.delete(item)

    suspend fun updateItem(item: Item) = itemDao.update(item)
}