package com.example.data.helper

import okhttp3.Headers

sealed class DataResult<out T> {
    data class Success<out T>(val data: T?, val headers: Headers?) : DataResult<T>()
    data class GenericError<out T>(val code: Int?, val error: T) : DataResult<Nothing>()
    object NetworkError : DataResult<Nothing>()
}
