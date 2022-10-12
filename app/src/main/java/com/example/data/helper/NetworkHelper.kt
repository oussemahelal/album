package com.example.data.helper

import com.example.data.BaseResponse
import com.example.data.local.ErrorEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> apiCall(apiCall: suspend () -> T): DataResult<T> {
    return withContext(Dispatchers.IO) {
        val result = try {
            DataResult.Success(apiCall.invoke(), null)
        } catch (throwable: Throwable){
            when(throwable){
                is IOException -> {
                    DataResult.NetworkError
                }
                is HttpException ->{
                    val code = throwable.code()
                    val body = throwable.response()?.errorBody()?.string()
                    handleHttpException(code, body)
                }
                else ->{
                    DataResult.GenericError(
                        null,
                        BaseResponse<Any>(null,ErrorEnum.SERVER_ERROR, null)
                    )
                }
            }
        }
        return@withContext result
    }
}

fun handleHttpException(code: Int, body: String?): DataResult<Nothing> {
    try {
        var model = body.toObject<BaseResponse<Any>>()

        if (model == null) {
            model = BaseResponse<Any>(null, ErrorEnum.SERVER_ERROR, null)
        }
        return DataResult.GenericError(code, model)

    } catch (e: Exception) {

        e.printStackTrace()

        return DataResult.GenericError(
            null,
            BaseResponse<Any>(null, ErrorEnum.SERVER_ERROR, null)
        )
    }
}