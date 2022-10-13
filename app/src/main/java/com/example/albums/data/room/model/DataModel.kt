package com.example.albums.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.albums.data.api.model.AlbumResponseModel

@Entity(tableName = "data_response_table")
data class DataModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "albumId")
    var albumId: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "url")
    var url: String = "",

    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String = ""
) {

    constructor(model: AlbumResponseModel) : this() {
        id = model.id
        albumId = model.albumId
        title = model.title ?: ""
        url = model.url ?: ""
        thumbnailUrl = model.thumbnailUrl ?: ""
    }
}