package com.example.albums.presentation.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albums.common.Resource
import com.example.albums.domain.state.AlbumsListState
import com.example.albums.domain.usecase.getalbums.GetAlbumsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeViewModel
@Inject
constructor(
    private val getAlbumsUseCase: GetAlbumsUseCase
) : ViewModel() {

    var albumsResult =
        MutableLiveData<AlbumsListState>()

    fun getAlbums() {
        getAlbumsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    albumsResult.postValue(AlbumsListState(albums = result.data ?: emptyList()))
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
}