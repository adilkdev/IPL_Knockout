package com.adil.iplknockout.data.repository

import android.net.Uri
import com.adil.iplknockout.data.models.Team
import javax.inject.Inject

/**
 * Created by Adil Khan on 18/03/2022
 */

class TeamsProvider @Inject constructor() : DataProvider {

    override fun provideTeams(): List<Team> =
        listOf (
            Team(1,
                Uri.parse("android.resource://com.adil.iplknockout/drawable/mi"),
                "Mumbai Indians"),
            Team(2,
                Uri.parse("android.resource://com.adil.iplknockout/drawable/rcb"),
                "Royal Challengers Bangalore"),
            Team(3,
                Uri.parse("android.resource://com.adil.iplknockout/drawable/dc"),
                "Delhi Capitals"),
            Team(4,
                Uri.parse("android.resource://com.adil.iplknockout/drawable/sh"),
                "Sunrisers Hyderabad"),
            Team(5,
                Uri.parse("android.resource://com.adil.iplknockout/drawable/rr"),
                "Rajasthan Royals"),
            Team(6,
                Uri.parse("android.resource://com.adil.iplknockout/drawable/csk"),
                "Chennai Super Kings"),
            Team(7,
                Uri.parse("android.resource://com.adil.iplknockout/drawable/kp"),
                "Kings XI Punjab"),
            Team(8,
                Uri.parse("android.resource://com.adil.iplknockout/drawable/kr"),
                "Kolkata Knight Riders"),
        )
            .shuffled()

}