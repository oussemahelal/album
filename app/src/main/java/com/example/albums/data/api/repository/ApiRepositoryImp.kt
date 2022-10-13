package com.example.albums.data.api.repository

import com.example.albums.data.api.model.AlbumResponseModel
import com.example.albums.data.api.service.ApiService
import javax.inject.Inject


class ApiRepositoryImp
@Inject
constructor(private val apiService: ApiService) : ApiRepository {

    override suspend fun getListAlbums(): List<AlbumResponseModel> {
        return apiService.getListAlbums()
    }
}