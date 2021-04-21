package com.example.habitnote.di

import com.example.data.HabitInteractor
import com.example.data.HabitNetworkRepository
import com.example.data.HabitRepository
import dagger.Module
import dagger.Provides

@Module
class HabitInteractorModule {

    @Provides
    fun provideHabitInteractor(
        habitRepository: HabitRepository,
        habitNetworkRepository: HabitNetworkRepository
    ): HabitInteractor {
        return HabitInteractor(habitRepository, habitNetworkRepository)
    }
}