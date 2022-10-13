package com.example.albums.domain.state

import com.example.albums.data.api.model.AlbumResponseModel

data class AlbumsListState(
    val isLoading : Boolean= false,
    val albums: List<AlbumResponseModel> = emptyList(),
    val error : String = ""
)