package com.droidapp;

import android.app.Application;

import com.droidnet.InternetAvailabilityChecker;

/**
 * Created by aa on 29/04/17.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InternetAvailabilityChecker.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        InternetAvailabilityChecker.getInstance().removeAllInternetConnectivityChangeListeners();
    }
}
