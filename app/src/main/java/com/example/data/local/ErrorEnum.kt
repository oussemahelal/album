package com.example.data.local

import com.fasterxml.jackson.annotation.JsonProperty

enum class ErrorEnum(val value: String) {

    @JsonProperty("SERVER_ERROR")
    SERVER_ERROR("SERVER_ERROR")
}