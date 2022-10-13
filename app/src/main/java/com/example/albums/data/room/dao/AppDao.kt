package com.example.albums.data.room.dao


import androidx.room.*
import com.example.albums.data.room.model.DataModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModel(vararg model: DataModel)

    @Query("SELECT * FROM data_response_table WHERE id = :id")
    suspend fun getModelById(id: Int): DataModel

    @Transaction
    suspend fun createAll(objects: List<DataModel>) = objects.forEach {insertModel(it)}

    @Query("SELECT * FROM data_response_table")
    suspend fun getAll(): Array<DataModel>
}