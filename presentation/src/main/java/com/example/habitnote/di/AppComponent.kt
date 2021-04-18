package com.example.habitnote.di

import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class,
    HabitInteractorModule::class])
interface AppComponent {
    val context: Context

    fun viewModelComponent(
            viewModelListHabitModel: ViewModelListHabitModule,
            activityModule: ActivityModule): ViewModelComponent
}