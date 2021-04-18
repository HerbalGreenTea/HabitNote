package com.example.habitnote.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habitnote.ViewModels.SharedViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelSharedModule {

    @Provides
    fun providesSharedViewModel(activity: FragmentActivity): SharedViewModel {
        @Suppress("UNCHECKED_CAST")
        return ViewModelProvider(activity, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SharedViewModel() as T
            }
        }).get(SharedViewModel::class.java)
    }
}