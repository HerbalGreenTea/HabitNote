package com.example.habitnote

import android.app.Application
import com.example.habitnote.di.AppComponent
import com.example.habitnote.di.ContextModule
import com.example.habitnote.di.DaggerAppComponent

class App: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        settingDagger()
    }

    private fun settingDagger() {

        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .build()
    }
}