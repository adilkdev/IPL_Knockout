package com.adil.iplknockout.data.models

/**
 * Created by Adil Khan on 19/02/2022
 */

data class TeamRank(
    val team: Team,
    val rank: RANK
)

enum class RANK {
    FIRST, SECOND, THIRD
}
