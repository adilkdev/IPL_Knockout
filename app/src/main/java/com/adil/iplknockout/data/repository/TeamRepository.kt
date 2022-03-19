package com.adil.iplknockout.data.repository

import com.adil.iplknockout.data.models.Team
import com.adil.iplknockout.utils.TeamsPairGeneratorRandom
import javax.inject.Inject

/**
 * Created by Adil Khan on 18/03/2022
 */

class TeamRepository @Inject constructor(private val dataProvider: DataProvider) {

    fun getAllTeams() = dataProvider.provideTeams()

    fun getAllTeamPairs(list: List<Team> = getAllTeams()) =
        TeamsPairGeneratorRandom(list).generatePairsOfTeams()

}