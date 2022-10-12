package com.example.albums.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_response")
class DataModel {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private val id : Int? = null

    @ColumnInfo(name = "albumId")
    private val albumId : Int? = null

    @ColumnInfo(name = "title")
    private val title : String? = null

    @ColumnInfo(name = "url")
    private val url : String? = null

    @ColumnInfo(name = "thumbnailUrl")
    private val thumbnailUrl : String? = null
}