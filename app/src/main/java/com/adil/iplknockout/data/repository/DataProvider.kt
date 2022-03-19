package com.adil.iplknockout.data.repository

import com.adil.iplknockout.data.models.Team

/**
 * Created by Adil Khan on 18/03/2022
 */

interface DataProvider {

    fun provideTeams() : List<Team>

}