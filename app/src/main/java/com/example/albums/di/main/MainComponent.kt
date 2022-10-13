package com.example.albums.di.main

import com.example.albums.presentation.MainActivity
import com.example.albums.presentation.fragment.HomeFragment
import dagger.Subcomponent

@MainScope
@Subcomponent(
    modules = [
        MainViewModelModule::class
    ]
)
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)

}