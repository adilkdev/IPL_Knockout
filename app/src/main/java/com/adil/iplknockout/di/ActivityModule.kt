package com.adil.iplknockout.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.adil.iplknockout.data.repository.DataProvider
import com.adil.iplknockout.data.repository.TeamRepository
import com.adil.iplknockout.data.repository.TeamsProvider
import com.adil.iplknockout.dispatcher.RealCoroutineDispatcherProvider
import com.adil.iplknockout.ui.MainActivity
import com.adil.iplknockout.ui.MainViewModel
import com.adil.iplknockout.utils.BaseViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun getMainViewModel(
        @ActivityContext context: Context,
        teamRepository: TeamRepository,
        dispatcherProvider: RealCoroutineDispatcherProvider
    ) =
        ViewModelProvider(context as MainActivity,
            BaseViewModelFactory {
                MainViewModel(teamRepository, dispatcherProvider)
            })[MainViewModel::class.java]

    @Provides
    fun getDataProvider() : DataProvider = TeamsProvider()

}