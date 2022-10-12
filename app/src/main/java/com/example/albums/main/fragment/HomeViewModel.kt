package com.example.albums.main.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.BaseResponse
import com.example.data.api.model.AlbumResponseModel
import com.example.data.api.repository.ApiRepository
import com.example.data.helper.DataResult
import com.example.data.local.ErrorEnum
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel
@Inject
constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    var albumsResult =
        MutableLiveData<List<AlbumResponseModel>?>()
    var errorMessage = MutableLiveData<BaseResponse<*>?>()

    fun getAlbums() {
        viewModelScope.launch {
            when (val response = apiRepository.getListAlbums()) {
                is DataResult.Success -> {
                    albumsResult.postValue(response.data)
                }
                is DataResult.GenericError<*> -> {
                    errorMessage.postValue(response.error as BaseResponse<*>)
                }
                DataResult.NetworkError -> {
                    errorMessage.postValue(BaseResponse<Any>(null, ErrorEnum.SERVER_ERROR, null))
                }
            }
        }
    }
}