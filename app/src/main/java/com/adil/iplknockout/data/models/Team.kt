package com.adil.iplknockout.data.models

import android.net.Uri

/**
 * Created by Adil Khan on 18/03/2022
 */

data class Team(
    val id: Int,
    val uriImage: Uri,
    val teamName: String
)