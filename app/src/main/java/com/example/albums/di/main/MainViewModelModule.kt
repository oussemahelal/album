package com.example.albums.di.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.albums.di.main.extension.CommonViewModelKey
import com.example.albums.di.main.factories.MainViewModelProviderFactory
import com.example.albums.presentation.fragment.HomeViewModel
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