package com.droidapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.droidnet.new.Event
import com.droidnet.new.NetworkEvents
import com.droidnet.new.NetworkState
import com.droidnet.new.NetworkStateHolder

class Main2Activity : AppCompatActivity() {
    private var mTvStatus: TextView? = null
    private var previousSate = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mTvStatus = findViewById(R.id.tv_status)


        NetworkEvents.observe(this, Observer {
            if (it is Event.ConnectivityEvent)
                handleConnectivityChange(it.state)
        })
    }

    private fun handleConnectivityChange(networkState: NetworkState) {
        if (networkState.isConnected && !previousSate) {
            mTvStatus!!.text = "connected"
        }

        if (!networkState.isConnected && previousSate) {
            mTvStatus!!.text = "not connected"
        }

        previousSate = networkState.isConnected
    }

    override fun onResume() {
        super.onResume()
        handleConnectivityChange(NetworkStateHolder)
    }
}