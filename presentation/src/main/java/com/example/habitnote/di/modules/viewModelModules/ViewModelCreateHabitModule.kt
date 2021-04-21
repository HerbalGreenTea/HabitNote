package com.example.habitnote.di.modules.viewModelModules

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habitnote.ViewModels.CreateHabitViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelCreateHabitModule {

    @Provides
    fun provideCreateHabitViewModel(activity: FragmentActivity): CreateHabitViewModel {
        @Suppress("UNCHECKED_CAST")
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CreateHabitViewModel() as T
            }
        }).get(CreateHabitViewModel::class.java)
    }
}