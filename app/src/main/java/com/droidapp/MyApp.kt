package com.droidapp

import android.app.Application
import com.droidnet.new.NetworkStateHolder.DroidNetConnectivityBroadcaster

/**
 * Created by aa on 29/04/17.
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //register DroidNet observer
        DroidNetConnectivityBroadcaster()
    }

}