package com.adil.iplknockout.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Adil Khan on 18/03/2022
 */

interface CoroutineDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfirmed: CoroutineDispatcher
}
