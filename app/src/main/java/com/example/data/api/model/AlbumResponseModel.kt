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
    val id : Int? = null,
    @JsonProperty("albumId")
    val albumId : Int? = null,
    @JsonProperty("title")
    val title : String? = null,
    @JsonProperty("url")
    val url : String? = null,
    @JsonProperty("thumbnailUrl")
    val thumbnailUrl : String? = null,

)
