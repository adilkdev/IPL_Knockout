package com.adil.iplknockout.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.adil.iplknockout.data.repository.TeamRepository
import com.adil.iplknockout.dispatcher.CoroutineDispatcherProvider
import com.adil.iplknockout.ui.main_activity.MainActivity
import com.adil.iplknockout.ui.main_activity.MainViewModel
import com.adil.iplknockout.utils.BaseViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

/**
 * Created by Adil Khan on 18/03/2022
 */

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun getMainViewModel(
        @ActivityContext context: Context,
        teamRepository: TeamRepository,
        dispatcherProvider: CoroutineDispatcherProvider
    ) =
        ViewModelProvider(context as MainActivity,
            BaseViewModelFactory {
                MainViewModel(teamRepository, dispatcherProvider)
            })[MainViewModel::class.java]

}