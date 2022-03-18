package com.adil.iplknockout.data.repository

import javax.inject.Inject

class TeamRepository @Inject constructor(private val dataProvider: TeamsProvider) {

    suspend fun getAllTeams() = dataProvider.provideTeams()

}