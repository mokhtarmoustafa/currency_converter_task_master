package com.mokhtar.currencyconverterapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Network Utility to detect availability or unavailability of Internet connection
 */
object NetworkUtils {

    private val networkLiveData: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Returns instance of [LiveData] which can be observed for network changes.
     */
    fun getNetworkLiveData(context: Context, manager: ConnMan): LiveData<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                networkLiveData.postValue(true)
            }

            override fun onLost(network: Network) {
                networkLiveData.postValue(false)
            }
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            manager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val builder = NetworkRequest.Builder()
            manager.registerNetworkCallback(builder.build(), networkCallback)
        }

        val activeNetwork: NetworkInfo? = manager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        networkLiveData.postValue(isConnected)

        return networkLiveData
    }
}

interface ConnMan {
    fun registerDefaultNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback)
    fun registerNetworkCallback(
        request: NetworkRequest,
        networkCallback: ConnectivityManager.NetworkCallback
    )

    val activeNetworkInfo: NetworkInfo?
}


class FakeConManager(context: Context) : ConnMan {
    override val activeNetworkInfo: NetworkInfo?
        get() = null


    var isRegistedCalled = false

    override fun registerNetworkCallback(
        request: NetworkRequest,
        networkCallback: ConnectivityManager.NetworkCallback
    ) {
        isRegistedCalled = true
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun registerDefaultNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback) {
        isRegistedCalled = true
    }
}

class ConManagerReal(context: Context) : ConnMan {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val activeNetworkInfo: NetworkInfo?
        get() = connectivityManager.activeNetworkInfo

    override fun registerNetworkCallback(
        request: NetworkRequest,
        networkCallback: ConnectivityManager.NetworkCallback
    ) {
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun registerDefaultNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }
}
