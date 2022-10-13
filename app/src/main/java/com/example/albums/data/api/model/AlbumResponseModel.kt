package com.example.albums.data.api.model

import com.google.gson.annotations.SerializedName


data class AlbumResponseModel(

    @SerializedName("id")
    var id : Int = 0,
    @SerializedName("albumId")
    var albumId : Int = 0,
    @SerializedName("title")
    var title : String? = null,
    @SerializedName("url")
    var url : String? = null,
    @SerializedName("thumbnailUrl")
    var thumbnailUrl : String? = null,

)
