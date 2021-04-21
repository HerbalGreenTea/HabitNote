package com.example.habitnote.di.modules

import com.example.data.useCases.HabitInteractor
import com.example.data.useCases.HabitNetworkRepository
import com.example.data.useCases.HabitDatabaseRepository
import dagger.Module
import dagger.Provides

@Module
class HabitInteractorModule {

    @Provides
    fun provideHabitInteractor(
        habitRepository: HabitDatabaseRepository,
        habitNetworkRepository: HabitNetworkRepository
    ): HabitInteractor {
        return HabitInteractor(habitRepository, habitNetworkRepository)
    }
}