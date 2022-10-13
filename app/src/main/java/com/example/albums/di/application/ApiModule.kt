package com.example.albums.di.application

import com.example.albums.data.api.repository.ApiRepository
import com.example.albums.data.api.repository.ApiRepositoryImp
import com.example.albums.data.api.service.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideApiRepository(
        apiService: ApiService
    ): ApiRepository {
        return ApiRepositoryImp(apiService)
    }

}