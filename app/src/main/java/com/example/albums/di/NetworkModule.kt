package com.example.albums.di

import android.app.Application
import com.example.albums.BuildConfig
import com.example.data.helper.RequestInterceptor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(
        application: Application
    ): OkHttpClient {

        val client = OkHttpClient.Builder()

        if (BuildConfig.DEBUG ) {
            client.addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            ).addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            )
        }

        return client
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                RequestInterceptor(
                    application.applicationContext
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://static.leboncoin.fr/")
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