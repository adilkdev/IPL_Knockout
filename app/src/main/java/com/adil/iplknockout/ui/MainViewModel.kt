package com.adil.iplknockout.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adil.iplknockout.data.models.Team
import com.adil.iplknockout.data.repository.TeamRepository
import com.adil.iplknockout.dispatcher.RealCoroutineDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel(
    private val teamRepository: TeamRepository,
    private val dispatcherProvider: RealCoroutineDispatcherProvider
) : ViewModel() {

    private lateinit var teamList: List<Team>

    init {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                teamList = teamRepository.getAllTeams()
            }
        }
    }

    fun setup() {

    }

    fun getTeamList() = teamList.toMutableList()

}