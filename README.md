# DroidNet [ ![Download](https://api.bintray.com/packages/jobgetabu/maven/droidnet/images/download.svg) ](https://bintray.com/jobgetabu/maven/droidnet/_latestVersion)  [![Build Status](https://travis-ci.org/JobGetabu/DroidNet.svg?branch=master)](https://travis-ci.org/JobGetabu/DroidNet)  [![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FJobGetabu%2FDroidNet.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FJobGetabu%2FDroidNet?ref=badge_shield)  [![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](LICENSE.md)
DroidNet is an Android Networking Library listening for network connection state and Internet connectivity with assumption that active internet connection or not. Connecting to a network doesn’t necessarily mean that device has active internet connection 

### Spread Some :heart:
[![GitHub followers](https://img.shields.io/github/followers/JobGetabu.svg?style=social&label=Follow)](https://github.com/JobGetabu)  [![Twitter Follow](https://img.shields.io/twitter/follow/job_getabu.svg?style=social)](https://twitter.com/job_getabu)

## Getting Started

These instructions will help you set up this library easily on your current project and working in no time. You only need a few configurations to start working!

## Installing

To be able to use the following library, you will need to add the following gradle dependency in your
#### build.gradle Project level

```
 repositories {
        jcenter()
    }
```

#### build.gradle  module

```
implementation 'com.job:droidnet:2.0.0'
```
That is the basic set up needed to be able to use the library in your applications!

## Permissions

First, we need to add these permission to our Android Manifest file :

```
<uses-permission android:name="android.permission.INTERNET" />
```

That's it, you have set up the required permissions and ready to go!

## Usage

In your Main Application

```java
import com.droidnet.DroidNet;
...


public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DroidNet.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        DroidNet.getInstance().removeAllInternetConnectivityChangeListeners();
    }
}
```

In your Activity

```java
import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
...


public class MainActivity extends AppCompatActivity implements DroidListener {

    //...

    private DroidNet mDroidNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //...
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
            //do Stuff with internet
            netIsOn();
        } else {
            //no internet
            netIsOff();
        }
    }

    private void netIsOn(){...}

    private void netIsOff(){...}
}
```


Take a look at this [sample](https://github.com/JobGetabu/DroidNet/tree/master/app) project which has a sample implementation of the library.

## Contributing and Issues

Please feel free to [contribute](https://github.com/JobGetabu/DroidNet/settings/collaboration) or open [issues](https://github.com/JobGetabu/DroidNet/issues), if any and I will be happy to help out!

## Proguard

You are all set.

## Stargazers over time

[![Stargazers over time](https://starchart.cc/JobGetabu/DroidNet.svg)](https://starchart.cc/JobGetabu/DroidNet)


### TODO
- [ ] add kotlin support
- [ ] use lifecycle aware components
- [ ] livedata
- [ ] android Q and latest permissions
