package com.example.habitnote.di.сomponents

import android.content.Context
import com.example.habitnote.di.modules.*
import com.example.habitnote.di.modules.viewModelModules.ViewModelListHabitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class,
    HabitDatabaseRepositoryModule::class,
    HabitNetworkRepositoryModule::class,
    HabitInteractorModule::class])
interface AppComponent {
    val context: Context

    fun viewModelComponent(
        viewModelListHabitModel: ViewModelListHabitModule,
        activityModule: FragmentActivityModule
    ): ViewModelComponent
}