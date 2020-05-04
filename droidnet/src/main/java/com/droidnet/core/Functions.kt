package com.droidnet.core

import android.util.Log

/**
 * just like runCatching but without result
 * @see runCatching
 */
internal inline fun <T> T.safeRun(TAG: String = "", block: T.() -> Unit) {
    try {
        block()
    } catch (e: Throwable) {
        //ignore but log it
        Log.e(TAG, e.toString())
    }
}