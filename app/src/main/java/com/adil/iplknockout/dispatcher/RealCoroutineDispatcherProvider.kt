package com.adil.iplknockout.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

open class RealCoroutineDispatcherProvider @Inject constructor() {
    val main by lazy { Dispatchers.Main }
    val io by lazy { Dispatchers.IO }
    val default by lazy { Dispatchers.Default }
    val unconfirmed by lazy { Dispatchers.Unconfined }
}
