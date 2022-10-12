package com.example.albums.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.albums.room.dao.AppDao
import com.example.albums.room.model.DataModel

@Database(entities = [DataModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}