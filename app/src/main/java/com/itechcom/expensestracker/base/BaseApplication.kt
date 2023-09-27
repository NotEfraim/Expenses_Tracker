package com.itechcom.expensestracker.base

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FacebookSdk.apply {
            sdkInitialize(this@BaseApplication)
            setAutoInitEnabled(true)
            fullyInitialize()
        }

    }
}