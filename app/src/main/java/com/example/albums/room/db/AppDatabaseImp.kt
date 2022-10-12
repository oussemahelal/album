package com.example.albums.room.db

import com.example.albums.room.dao.AppDao
import javax.inject.Inject

class AppDatabaseImp
@Inject
constructor(
    private val appDao: AppDao
) : AppDatabaseRepository {
    override suspend fun insertListTitles(list: List<String>) {
        appDao.insertListTitles(list)
    }

    override suspend fun updateListTitles(list: List<String>) {
        appDao.updateListTitles(list)
    }

    override suspend fun get() {
        appDao.getListTitles()
    }
}