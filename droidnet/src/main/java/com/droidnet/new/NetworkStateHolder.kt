package com.droidnet.new

import android.app.Application
import android.content.Context
import android.net.*
import com.droidnet.core.ActivityLifecycleCallbacksImp
import com.droidnet.core.NetworkCallbackImp
import com.droidnet.core.NetworkStateImp
import com.droidnet.new.NetworkState



object NetworkStateHolder : NetworkState {

    private lateinit var holder: NetworkStateImp


    override val isConnected: Boolean
        get() = holder.isConnected
    override val network: Network?
        get() = holder.network
    override val networkCapabilities: NetworkCapabilities?
        get() = holder.networkCapabilities
    override val linkProperties: LinkProperties?
        get() = holder.linkProperties


    /**
     * This starts the broadcast of network events to NetworkState and all Activity implementing NetworkConnectivityListener
     * @see NetworkState
     * @see NetworkConnectivityListener
     */
    fun Application.DroidNetConnectivityBroadcaster() {

        holder = NetworkStateImp()

        //register tje Activity Broadcaster
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImp(holder))


        //get connectivity manager
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //register to network events
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), NetworkCallbackImp(holder))

    }


}