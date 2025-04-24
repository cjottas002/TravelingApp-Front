package es.travelworld.traveling.core.network

import es.travelworld.traveling.core.network.interfaces.INetworkChecker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkExecutor @Inject constructor(private val networkChecker: INetworkChecker) {

    fun executeWithNetworkCheck(
        onlineAction: () -> Unit,
        offlineAction: () -> Unit
    ) {
        if (networkChecker.isInternetAvailable())
            onlineAction()
        else
            offlineAction()

    }
}