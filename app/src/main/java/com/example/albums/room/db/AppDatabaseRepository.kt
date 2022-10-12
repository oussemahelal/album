package com.example.albums.room.db

interface AppDatabaseRepository
 {
     suspend fun insertListTitles(list : List<String>)
     suspend fun updateListTitles(list : List<String>)
     suspend fun get()
}