package com.example.albums.data.api.repository

import com.example.albums.data.api.model.AlbumResponseModel


interface ApiRepository {
    suspend fun getListAlbums(): List<AlbumResponseModel>
}