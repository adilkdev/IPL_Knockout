package com.adil.iplknockout.di

import com.adil.iplknockout.dispatcher.CoroutineDispatcherProvider
import com.adil.iplknockout.dispatcher.RealCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

}