package com.example.data.api.repository

import com.example.data.api.model.AlbumResponseModel
import com.example.data.helper.DataResult


interface ApiRepository {
    suspend fun getListAlbums(): DataResult<List<AlbumResponseModel>>
}