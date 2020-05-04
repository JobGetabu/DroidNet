package com.droidnet.new

import android.net.LinkProperties
import android.net.NetworkCapabilities

sealed class Event {
    abstract val state: NetworkState

    class ConnectivityEvent(override val state: NetworkState) : Event()
    class NetworkCapabilityEvent(override val state: NetworkState, val old: NetworkCapabilities?) : Event()
    class LinkPropertyChangeEvent(override val state: NetworkState, val old: LinkProperties?) : Event()
}