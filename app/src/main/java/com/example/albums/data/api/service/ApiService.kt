package com.example.albums.data.api.service

import com.example.albums.data.api.model.AlbumResponseModel
import retrofit2.http.GET

interface ApiService {

    @GET("img/shared/technical-test.json")
    suspend fun getListAlbums(): List<AlbumResponseModel>
}