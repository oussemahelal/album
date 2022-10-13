package com.example.albums.di.application

import com.example.albums.common.Constants
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val client = OkHttpClient.Builder()

        return client
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                JacksonConverterFactory.create(
                    ObjectMapper()
                        .configure(
                            DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true
                        )
                        .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true))
                )
            )
            .client(okHttpClient)
    }

}