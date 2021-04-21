package com.example.habitnote.di.modules

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides

@Module
class FragmentActivityModule(private val activity: FragmentActivity) {

    @Provides
    fun provideActivity(): FragmentActivity = activity
}