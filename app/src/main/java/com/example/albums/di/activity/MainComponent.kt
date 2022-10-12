package com.example.albums.di.activity

import com.example.albums.main.MainActivity
import com.example.albums.main.fragment.HomeFragment
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