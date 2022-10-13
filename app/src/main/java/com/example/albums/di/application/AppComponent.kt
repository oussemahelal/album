package com.example.albums.di.application

import android.app.Application
import com.example.albums.di.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RoomModule::class,
        NetworkModule::class,
        ApiModule::class,
        SubComponentModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
}