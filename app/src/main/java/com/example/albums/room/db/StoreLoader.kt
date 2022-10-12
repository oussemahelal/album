package com.example.albums.room.db

import android.content.Context
import androidx.room.Room

class StoreLoader {
    companion object {

        @Volatile
        private var store: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return store ?: synchronized(this) {
                store ?: buildDatabase(context).also { store = it }
            }
        }

        private fun buildDatabase(
            context: Context
        ): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "App_Database"
            ).allowMainThreadQueries().build()
        }
    }
}