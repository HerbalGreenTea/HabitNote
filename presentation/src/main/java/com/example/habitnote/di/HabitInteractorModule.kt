package com.example.habitnote.di

import android.content.Context
import com.example.data.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HabitInteractorModule {

    @Provides
    @Singleton
    fun provideHabitDatabase(context: Context): HabitDatabase {
        return HabitDatabase.getDatabase(context)
    }

    @Provides
    fun provideHabitDao(habitDatabase: HabitDatabase): HabitDao {
        return habitDatabase.habitDao()
    }

    @Provides
    fun provideHabitRepository(habitDao: HabitDao): HabitRepository {
        return HabitRepositoryImpl(habitDao)
    }

    @Provides
    fun provideHabitInteractor(habitRepository: HabitRepository): HabitInteractor {
        return HabitInteractor(habitRepository)
    }
}