package com.example.albums.di

import com.example.data.api.ApiService
import com.example.data.api.repository.ApiRepository
import com.example.data.api.repository.ApiRepositoryImp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ApiModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideAuthApiService(retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideApiRepository(
        apiService: ApiService
    ): ApiRepository {
        return ApiRepositoryImp(apiService)
    }

}