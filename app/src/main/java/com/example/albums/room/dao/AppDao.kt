package com.example.albums.room.dao


import androidx.room.*
import com.example.albums.room.model.DataModel

@Dao
interface AppDao {
    @Query("Select * from data_response")
    suspend fun getListTitles(): List<DataModel>

    @Update
    suspend fun updateListTitles(list: List<String>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListTitles(list: List<String>)
}