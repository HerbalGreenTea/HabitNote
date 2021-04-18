package com.example.habitnote.di

import androidx.fragment.app.FragmentActivity
import com.example.habitnote.CreateHabitFragment
import com.example.habitnote.FilterHabitsFragment
import com.example.habitnote.HabitListFragment
import dagger.Subcomponent

@Subcomponent(modules = [
    ActivityModule::class,
    ViewModelListHabitModule::class,
    ViewModelCreateHabitModule::class,
    ViewModelSharedModule::class])
interface ViewModelComponent {
    val activity: FragmentActivity

    fun inject(habitListFragment: HabitListFragment)
    fun inject(filterHabitsFragment: FilterHabitsFragment)
    fun inject(createHabitFragment: CreateHabitFragment)
}