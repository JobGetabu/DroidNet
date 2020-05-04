package com.droidapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity implements DroidListener {

    private ConstraintLayout main;
    private MaterialButton btn;
    private TextView txt;
    private View vm;
    private boolean isConnected;

    private DroidNet mDroidNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = findViewById(R.id.main_content);
        btn = findViewById(R.id.button);
        txt = findViewById(R.id.textView);
        vm = findViewById(R.id.vm);

        mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("...");
                recreate();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        this.isConnected = isConnected;
        if (isConnected) {
            netIsOn();
        } else {
            netIsOff();
        }
    }

    private void netIsOn(){
        main.setBackgroundColor(getResources().getColor(R.color.online));
        txt.setText("Connected");
        txt.setTextColor(getResources().getColor(R.color.green));
    }

    private void netIsOff(){
        main.setBackgroundColor(getResources().getColor(R.color.offline));
        txt.setTextColor(getResources().getColor(R.color.dark));
        txt.setText("DisConnected");
    }
}
