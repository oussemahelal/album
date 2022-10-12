package com.example.albums.di


import com.example.albums.di.activity.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
class SubComponentModule