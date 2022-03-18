package com.adil.iplknockout.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

open class RealCoroutineDispatcherProvider @Inject constructor() : CoroutineDispatcherProvider {
    override val main: CoroutineDispatcher by lazy { Dispatchers.Main }
    override val io: CoroutineDispatcher by lazy { Dispatchers.IO }
    override val default: CoroutineDispatcher by lazy { Dispatchers.Default }
    override val unconfirmed: CoroutineDispatcher by lazy { Dispatchers.Unconfined }
}
