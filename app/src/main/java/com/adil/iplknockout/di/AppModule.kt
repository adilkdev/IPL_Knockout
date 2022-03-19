package com.adil.iplknockout.di

import com.adil.iplknockout.data.models.Team
import com.adil.iplknockout.data.repository.DataProvider
import com.adil.iplknockout.data.repository.TeamRepository
import com.adil.iplknockout.data.repository.TeamsProvider
import com.adil.iplknockout.dispatcher.CoroutineDispatcherProvider
import com.adil.iplknockout.dispatcher.RealCoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Adil Khan on 18/03/2022
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun getTeams(teamRepository: TeamRepository): List<Team> =
        teamRepository.getAllTeams()

    @Provides
    fun getDataProvider(): DataProvider = TeamsProvider()

    /**
     * preferring this way of providing interface implementation over the use of
     * @Binds annotation as this seems much easier.
     */
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcherProvider =
        RealCoroutineDispatcherProvider()
}