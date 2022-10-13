package com.example.albums.di.application


import com.example.albums.di.main.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
class SubComponentModule