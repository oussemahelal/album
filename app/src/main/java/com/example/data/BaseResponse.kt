package com.example.data

import com.example.data.local.ErrorEnum
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder(alphabetic = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class BaseResponse<out T : Any>(

    @JsonProperty("data")
    val data: T?,

    @JsonProperty("error")
    val error: ErrorEnum?,

    @JsonProperty("message")
    val message: String?,

    @JsonProperty("description")
    val description: Any? = null

)
