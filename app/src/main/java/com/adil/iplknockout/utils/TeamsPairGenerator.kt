package com.adil.iplknockout.utils

import com.adil.iplknockout.data.models.Team
import com.adil.iplknockout.data.models.TeamPair

/**
 * Created by Adil Khan on 18/03/2022
 */

class TeamsPairGeneratorRandom(private val teamList: List<Team>) : TeamsPairGenerator {

    private val list: MutableList<Team> = teamList.toMutableList()
    private val pairList = mutableListOf<TeamPair>()


    override fun generatePairsOfTeams(): List<TeamPair> {
        while (list.isNotEmpty()) {
            addAPair()
        }
        return pairList
    }

    private fun addAPair() {
        val firstTeam = removeTeamFromList()
        val secondTeam = removeTeamFromList()

        pairList.add(TeamPair(firstTeam, secondTeam))
    }

    private fun removeTeamFromList(): Team {
        val index = (0 until list.size).random()
        val team = list[index]
        list.removeAt(index)
        return team
    }

}

interface TeamsPairGenerator {
    fun generatePairsOfTeams(): List<TeamPair>
}