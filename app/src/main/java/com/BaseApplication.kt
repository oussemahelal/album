package com

import android.app.Application
import com.example.albums.di.AppComponent
import com.example.albums.di.DaggerAppComponent
import com.example.albums.di.activity.MainComponent

class BaseApplication: Application() {

    lateinit var appComponent: AppComponent
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