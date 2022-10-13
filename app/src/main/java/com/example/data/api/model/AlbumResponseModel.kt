package com.example.data.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AlbumResponseModel(

    @JsonProperty("id")
    var id : Int = 0,
    @JsonProperty("albumId")
    var albumId : Int = 0,
    @JsonProperty("title")
    var title : String? = null,
    @JsonProperty("url")
    var url : String? = null,
    @JsonProperty("thumbnailUrl")
    var thumbnailUrl : String? = null,

)
