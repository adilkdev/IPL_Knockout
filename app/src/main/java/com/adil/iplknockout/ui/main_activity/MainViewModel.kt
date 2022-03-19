package com.adil.iplknockout.ui.main_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adil.iplknockout.data.models.RANK
import com.adil.iplknockout.data.models.Team
import com.adil.iplknockout.data.models.TeamPair
import com.adil.iplknockout.data.models.TeamRank
import com.adil.iplknockout.data.repository.TeamRepository
import com.adil.iplknockout.dispatcher.CoroutineDispatcherProvider
import com.adil.iplknockout.utils.AppConstants
import com.adil.iplknockout.utils.MatchSimulator
import com.adil.iplknockout.utils.MatchSimulatorRandom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Adil Khan on 18/03/2022
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val teamRepository: TeamRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    /**
     * teamList is used to generate team pairs for a match at every successive round.
     */
    private lateinit var teamList: List<Team>

    /**
     * teamPairList stores the team pairs generated for matches.
     */
    private lateinit var teamPairList: List<TeamPair>

    /**
     * teamRankList stores the first, second and third position teams.
     */
    private lateinit var teamRankList: MutableList<TeamRank>

    /**
     *  Match simulator simulates the match based on some given logic.
     */
    private var matchSimulator: MatchSimulator = MatchSimulatorRandom()

    /**
     * The teamPairListLiveData is observed by the user interface to show the details of matches.
     */
    lateinit var teamPairListLiveData: MutableLiveData<List<TeamPair>>

    /**
     *  isGameOver is a live data which informs the ui that the game has finished.
     */
    lateinit var isGameOver: MutableLiveData<Boolean>

    init {
        setup()
    }

    /**
     *  setup initialises all the fields.
     */
    private fun setup() {
        viewModelScope.launch {
            withContext(dispatcherProvider.io) {
                teamList = teamRepository.getAllTeams()
                teamPairList = teamRepository.getAllTeamPairs(teamList)
            }
            teamRankList = mutableListOf()
            teamPairListLiveData = MutableLiveData<List<TeamPair>>()
            isGameOver = MutableLiveData<Boolean>()
            teamPairListLiveData.postValue(teamPairList)
        }
    }

    /**
     * @return immutable copy of teamList
     */
    fun getTeamList() = teamList.toList()

    /**
     * @return immutable copy of teamPairList
     */
    fun getTeamPairs() = teamPairList.toList()

    /**
     * @return immutable copy of list of teams with their rank in sorted manner.
     */
    fun getTeamRankList() = teamRankList.apply { sortBy { it.rank.ordinal } }.toList()

    /**
     * @return generates a list of team pairs which will play the matches.
     */
    private fun generateTeamPairs(list: List<Team> = teamList) =
        teamRepository.getAllTeamPairs(list).also { teamPairList = it }

    /**
     * gets the list of winner teams from every match
     */
    fun getWinnersOfMatch(teamPairList: List<TeamPair> = getTeamPairs()) {
        val teamList = matchSimulator.getWinnersOfMatch(teamPairList)
        updateTeamData(teamList)
    }

    /**
     * Gets the list of winner and loser teams from every match.
     * This method is required only when we have to pick the team for third position.
     */
    fun getWinnersAndLosersOfMatch(
        teamPairList: List<TeamPair> = getTeamPairs(),
        isForThirdPos: Boolean
    ) {
        val result = matchSimulator.getWinnersAndLosersOfMatch(teamPairList)
        val winnerList = result[AppConstants.ZEROTH_INDEX]
        val loserList = result[AppConstants.FIRST_INDEX]

        /**
         * if the flag is true it means we are looking for the team with third position.
         */
        if (isForThirdPos) {
            val thirdPositionTeam = matchSimulator.simulateMatchBetweenTwoTeams(
                TeamPair(
                    loserList[AppConstants.ZEROTH_INDEX],
                    loserList[AppConstants.FIRST_INDEX]
                )
            ).winner
            teamRankList.add(TeamRank(thirdPositionTeam, RANK.THIRD))
        }

        /**
         * if loserList or winnerList size == 1 , it means that we had the final match
         * and hence we have the first and second position for teams.
         */
        if (loserList.size == 1) {
            val firstPositionTeam = winnerList[AppConstants.ZEROTH_INDEX]
            val secondPositionTeam = loserList[AppConstants.ZEROTH_INDEX]
            teamRankList.add(TeamRank(firstPositionTeam, RANK.FIRST))
            teamRankList.add(TeamRank(secondPositionTeam, RANK.SECOND))
            isGameOver.postValue(true)
        }
        updateTeamData(winnerList)
    }

    /**
     * Updates the teamList by removing the losing teams and
     * generates new pairs for matches if there are more than one team left.
     */
    private fun updateTeamData(teamList: List<Team>) {
        this.teamList = teamList
        if (teamList.size > 1) {
            generateTeamPairs(teamList)
            teamPairListLiveData.postValue(teamPairList)
        }
    }

    /**
     * resets all the data to the initial stage.
     */
    fun restart() {
        setup()
    }

}