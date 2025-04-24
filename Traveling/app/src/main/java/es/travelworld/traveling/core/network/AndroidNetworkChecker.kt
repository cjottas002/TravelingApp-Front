package es.travelworld.traveling.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import es.travelworld.traveling.core.network.interfaces.INetworkChecker
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidNetworkChecker @Inject constructor(@ApplicationContext private val context: Context) : INetworkChecker {

    override fun isInternetAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = cm.activeNetwork ?: return false
            val capabilities = cm.getNetworkCapabilities(network) ?: return false
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        } else {
            @Suppress("DEPRECATION")
            val info = cm.activeNetworkInfo
            info?.isConnected == true
        }
    }
}