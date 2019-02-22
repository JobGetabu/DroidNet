package com.droidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.droidnet.DroidNet;
import com.droidnet.InternetConnectivityListener;


public class MainActivity extends AppCompatActivity implements InternetConnectivityListener {

    private TextView mTvStatus;
    private DroidNet mDroidNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mTvStatus = findViewById(R.id.tv_status);
        findViewById(R.id.btb_open_next_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

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
