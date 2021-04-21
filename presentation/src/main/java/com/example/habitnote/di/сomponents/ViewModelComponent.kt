package com.example.habitnote.di.сomponents

import androidx.fragment.app.FragmentActivity
import com.example.habitnote.fragments.CreateHabitFragment
import com.example.habitnote.fragments.FilterHabitsFragment
import com.example.habitnote.fragments.HabitListFragment
import com.example.habitnote.di.modules.FragmentActivityModule
import com.example.habitnote.di.modules.viewModelModules.ViewModelCreateHabitModule
import com.example.habitnote.di.modules.viewModelModules.ViewModelListHabitModule
import com.example.habitnote.di.modules.viewModelModules.ViewModelSharedModule
import dagger.Subcomponent

@Subcomponent(modules = [
    FragmentActivityModule::class,
    ViewModelListHabitModule::class,
    ViewModelCreateHabitModule::class,
    ViewModelSharedModule::class])
interface ViewModelComponent {
    val activity: FragmentActivity

    fun inject(habitListFragment: HabitListFragment)
    fun inject(filterHabitsFragment: FilterHabitsFragment)
    fun inject(createHabitFragment: CreateHabitFragment)
}