package com.example.habitnote.di

import android.content.Context
import com.example.habitnote.FilterHabitsFragment
import com.example.habitnote.HabitListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ContextModule::class,
    HabitInteractorModule::class])
interface AppComponent {
    val context: Context

    fun inject(habitListFragment: HabitListFragment)
    fun inject(filterHabitsFragment: FilterHabitsFragment)
}