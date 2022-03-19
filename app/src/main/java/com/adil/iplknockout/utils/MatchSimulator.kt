package com.adil.iplknockout.utils

import com.adil.iplknockout.data.models.MatchResult
import com.adil.iplknockout.data.models.Team
import com.adil.iplknockout.data.models.TeamPair

/**
 * Created by Adil Khan on 18/03/2022
 */

interface MatchSimulator {
    fun simulateMatchBetweenTwoTeams(teamPair: TeamPair): MatchResult
    fun getWinnersOfMatch(teamPairList: List<TeamPair>): List<Team>
    fun getWinnersAndLosersOfMatch(teamPairList: List<TeamPair>): Array<List<Team>>
}

class MatchSimulatorRandom : MatchSimulator {

    override fun simulateMatchBetweenTwoTeams(teamPair: TeamPair): MatchResult {
        return if ((0..1).random() == 0)
            MatchResult(teamPair.firstTeam, teamPair.secondTeam)
        else
            MatchResult(teamPair.secondTeam, teamPair.firstTeam)
    }

    override fun getWinnersOfMatch(teamPairList: List<TeamPair>): List<Team> {
        val winners = arrayListOf<Team>()
        teamPairList.forEach { teamPair ->
            winners.add(simulateMatchBetweenTwoTeams(teamPair).winner)
        }
        return winners
    }

    override fun getWinnersAndLosersOfMatch(teamPairList: List<TeamPair>): Array<List<Team>> {
        val winners = arrayListOf<Team>()
        val losers = arrayListOf<Team>()
        teamPairList.forEach { teamPair ->
            val matchResult = simulateMatchBetweenTwoTeams(teamPair)
            winners.add(matchResult.winner)
            losers.add(matchResult.loser)
        }
        return arrayOf(winners, losers)
    }


}