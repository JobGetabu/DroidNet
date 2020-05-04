package com.droidnet.core

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.droidnet.new.NetworkConnectivityListener
import com.droidnet.new.NetworkState

/**
 * This is the implementation Application.ActivityLifecycleCallbacksImp,
 * it calls the methods of NetworkConnectivityListener in the activity implementing it,
 * thus enabling
 * @see Application.ActivityLifecycleCallbacks
 */
internal class ActivityLifecycleCallbacksImp(private val networkState: NetworkState) :
    Application.ActivityLifecycleCallbacks {


    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityDestroyed(activity: Activity) {}

    override fun onActivityCreated(activity: Activity, p1: Bundle?) = safeRun(TAG) {
        if (activity !is LifecycleOwner) return

        if (activity is FragmentActivity)
            addLifecycleCallbackToFragments(
                activity,
                networkState
            )

        if (activity !is NetworkConnectivityListener || !activity.shouldBeCalled) return

        activity.onListenerCreated()

    }

    override fun onActivityResumed(activity: Activity) = safeRun {
        if (activity !is LifecycleOwner) return
        if (activity !is NetworkConnectivityListener) return

        activity.onListenerResume(networkState)
    }


    private fun addLifecycleCallbackToFragments(activity: FragmentActivity, networkState: NetworkState) = safeRun(
        TAG
    ) {

        val callback = object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentCreated(fm: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
                if (fragment !is NetworkConnectivityListener || !fragment.shouldBeCalled) return
                fragment.onListenerCreated()
            }

            override fun onFragmentResumed(fm: FragmentManager, fragment: Fragment) {
                if (fragment is NetworkConnectivityListener)
                    fragment.onListenerResume(networkState)
            }
        }

        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(callback, true)
    }

    companion object {
        const val TAG = "ActivityCallbacks"

    }
}