package com.example.smartkeeratest

import android.app.Application
import com.example.smartkeeratest.deps.AppContainer

class MyApp : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }

}