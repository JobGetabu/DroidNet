package com.droidapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.droidnet.new.Event
import com.droidnet.new.NetworkConnectivityListener
import com.google.android.material.button.MaterialButton

class BaseActivity : AppCompatActivity(), NetworkConnectivityListener {
    private var main: ConstraintLayout? = null
    private lateinit var btn: MaterialButton
    private lateinit var txt: TextView
    private var vm: View? = null
    private var isCon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main = findViewById(R.id.main_content)
        btn = findViewById(R.id.button)
        txt = findViewById(R.id.textView)
        vm = findViewById(R.id.vm)
        isCon = true

        btn.setOnClickListener {
            txt.text = "..."
            recreate()
        }
    }

    override fun networkConnectivityChanged(event: Event) {
        when (event) {
            is Event.ConnectivityEvent -> {
                if (event.state.isConnected) {
                    netIsOn()
                } else {
                    netIsOff()
                }
            }
        }
    }

    private fun netIsOn() {
        main!!.setBackgroundColor(resources.getColor(R.color.online))
        txt!!.text = "Connected"
        txt!!.setTextColor(resources.getColor(R.color.green))
    }

    private fun netIsOff() {
        main!!.setBackgroundColor(resources.getColor(R.color.offline))
        txt!!.setTextColor(resources.getColor(R.color.dark))
        txt!!.text = "DisConnected"
    }
}