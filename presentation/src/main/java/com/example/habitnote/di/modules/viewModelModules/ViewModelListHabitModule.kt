package com.example.habitnote.di.modules.viewModelModules

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.useCases.HabitInteractor
import com.example.habitnote.ViewModels.ListHabitViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelListHabitModule {

    @Provides
    fun providesListHabitViewModel(
        habitInteractor: HabitInteractor,
        activity: FragmentActivity): ListHabitViewModel {

        @Suppress("UNCHECKED_CAST")
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListHabitViewModel(habitInteractor) as T
            }
        }).get(ListHabitViewModel::class.java)
    }
}