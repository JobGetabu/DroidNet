package com.droidnet.new

import androidx.lifecycle.LiveData
import com.droidnet.new.Event

/**
 * This liveData enabling network connectivity monitoring
 * @see NetworkStateHolder to get current connection state
 */
object DroidNetEvents : LiveData<Event>() {

    internal fun notify(event: Event) {
        postValue(event)
    }

}