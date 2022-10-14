package com.example.albums.presentation.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albums.common.Resource
import com.example.albums.data.api.model.AlbumResponseModel
import com.example.albums.data.room.db.AppDatabase
import com.example.albums.data.room.model.DataModel
import com.example.albums.domain.state.AlbumsListState
import com.example.albums.domain.usecase.getalbums.GetAlbumsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel
@Inject
constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val appDatabase: AppDatabase
) : ViewModel() {

    var albumsResult =
        MutableLiveData<AlbumsListState>()

    fun getAlbums() {
        getAlbumsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    albumsResult.postValue(AlbumsListState(albums = result.data ?: emptyList()))

                    if (result.data != null && result.data.any()) {
                        val data: ArrayList<DataModel> = arrayListOf()
                        result.data.forEach { m -> data.add(DataModel(m)) }
                        saveToDatabase(data)
                    }
                }
                is Resource.GenericError -> {
                    albumsResult.postValue(
                        AlbumsListState(
                            error = result.message ?: " An error occurred"
                        )
                    )
                }
                is Resource.Loading -> {
                    albumsResult.postValue(AlbumsListState(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveToDatabase(list: List<DataModel>) {
        viewModelScope.launch {
            appDatabase.appDao().createAll(list)
        }
    }

    fun getFromDatabase() {
        viewModelScope.launch {
            val model = appDatabase.appDao().getAll()
            if (model.any()) {
                val data: ArrayList<AlbumResponseModel> = arrayListOf()
                model.forEach { m -> data.add(AlbumResponseModel(m)) }
                albumsResult.postValue(AlbumsListState(albums = data))
            }
        }
    }
}