package com.stolbov.emptyprojectstolbov

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

object NetworkLiveData : LiveData<Boolean>() {
    private lateinit var myApplication: Application

    private lateinit var networkRequest: NetworkRequest

    private var numConnected =
        0//небольшой костыль, нужен при переключении с wi-fi на моб сеть и наоборот

    override fun onActive() {
        super.onActive()
        getDetails()
    }

    fun init(application: Application) {
        myApplication = application
        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }

    private fun getDetails() {
        val cManager =
            myApplication.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cManager.registerNetworkCallback(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    numConnected++
                    super.onAvailable(network)
                    postValue(true)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    postValue(false)
                }

                override fun onLost(network: Network) {
                    numConnected = (numConnected - 1).coerceAtLeast(0)
                    super.onLost(network)
                    if (numConnected == 0) {
                        postValue(false)
                    }
                }
            })
    }
}