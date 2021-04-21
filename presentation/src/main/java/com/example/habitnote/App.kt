package com.example.habitnote

import android.app.Application
import com.example.habitnote.di.сomponents.AppComponent
import com.example.habitnote.di.modules.ContextModule
import com.example.habitnote.di.modules.HabitNetworkRepositoryModule
import com.example.habitnote.di.сomponents.DaggerAppComponent

class App: Application() {
    companion object {
        lateinit var appComponent: AppComponent

        private const val AUTHORIZATION = "Authorization"
        private const val KEY_TOKEN = "1334dc12-2507-4a3f-8a88-5459d63a3d30"
        private const val URL = "https://droid-test-server.doubletapp.ru/api/"
    }

    override fun onCreate() {
        super.onCreate()
        settingDagger()
    }

    private fun settingDagger() {
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(applicationContext))
            .habitNetworkRepositoryModule(
                HabitNetworkRepositoryModule(AUTHORIZATION, KEY_TOKEN, URL)
            )
            .build()
    }
}