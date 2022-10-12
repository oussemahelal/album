package com.example.albums.di

import android.app.Application
import androidx.room.Room
import com.example.albums.room.dao.AppDao
import com.example.albums.room.db.AppDatabase
import com.example.albums.room.db.AppDatabaseImp
import com.example.albums.room.db.AppDatabaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "App_Database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppDao(appDatabase: AppDatabase): AppDao {
        return appDatabase.appDao()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideAppRepository(
        appDao: AppDao
    ): AppDatabaseRepository {
        return AppDatabaseImp(appDao)
    }

}