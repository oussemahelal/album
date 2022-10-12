package com.example.data.api

import com.example.data.api.model.AlbumResponseModel
import retrofit2.http.GET

interface ApiService {

    @GET("https://static.leboncoin.fr/img/shared/technical-test.json")
    suspend fun getListAlbums(): List<AlbumResponseModel>
}