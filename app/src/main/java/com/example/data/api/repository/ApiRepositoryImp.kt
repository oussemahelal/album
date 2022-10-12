package com.example.data.api.repository

import com.example.data.api.ApiService
import com.example.data.api.model.AlbumResponseModel
import com.example.data.helper.DataResult
import com.example.data.helper.apiCall
import javax.inject.Inject


class ApiRepositoryImp
@Inject
constructor(private val apiService: ApiService) : ApiRepository {
    override suspend fun getListAlbums(): DataResult<List<AlbumResponseModel>> {
        return apiCall {
            apiService.getListAlbums()
        }
    }
}