package com.droidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.droidnet.DroidNet;
import com.droidnet.InternetConnectivityListener;


public class Main2Activity extends AppCompatActivity implements InternetConnectivityListener {

    private TextView mTvStatus;
    private DroidNet mDroidNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mTvStatus = findViewById(R.id.tv_status);

        mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        if (isConnected) {
            mTvStatus.setText("connected");
        } else {
            mTvStatus.setText("not connected");
        }
    }
}
