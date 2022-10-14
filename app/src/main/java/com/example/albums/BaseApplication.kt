package com.example.albums

import android.app.Application
import com.example.albums.di.application.AppComponent
import com.example.albums.di.application.DaggerAppComponent
import com.example.albums.di.main.MainComponent

class BaseApplication : Application() {

    private lateinit var appComponent: AppComponent
    private var mainComponent: MainComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

    }

    fun mainComponent(): MainComponent {
        if (mainComponent == null)
            mainComponent = appComponent.mainComponent().create()
        return mainComponent as MainComponent
    }
}