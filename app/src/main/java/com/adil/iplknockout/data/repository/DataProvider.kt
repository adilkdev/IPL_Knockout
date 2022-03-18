package com.adil.iplknockout.data.repository

import com.adil.iplknockout.data.models.Team

interface DataProvider {

    suspend fun provideTeams() : List<Team>

}