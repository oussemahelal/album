package com.example.albums.di.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.albums.di.activity.factories.MainViewModelProviderFactory
import com.example.albums.di.extension.CommonViewModelKey
import com.example.albums.main.fragment.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    abstract fun bindViewModelProviderFactory(providerFactory: MainViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @CommonViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}