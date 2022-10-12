package com.example.data.helper

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class RequestInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder()
            .url(
                request.url.toString()
            )
            .build()
        return chain.proceed(request)
    }
}