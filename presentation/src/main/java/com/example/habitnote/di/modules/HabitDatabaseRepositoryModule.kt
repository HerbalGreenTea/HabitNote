package com.example.habitnote.di.modules

import android.content.Context
import androidx.room.Room
import com.example.data.database.HabitDao
import com.example.data.database.HabitDatabase
import com.example.data.database.HabitDatabaseRepositoryImpl
import com.example.data.useCases.HabitDatabaseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HabitDatabaseRepositoryModule {

    @Provides
    @Singleton
    fun provideHabitDatabase(context: Context): HabitDatabase {
        return Room.databaseBuilder(
                context,
                HabitDatabase::class.java,
                "habit_database"
        ).build()
    }

    @Provides
    fun provideHabitDao(habitDatabase: HabitDatabase): HabitDao {
        return habitDatabase.habitDao()
    }

    @Provides
    fun provideHabitRepository(habitDao: HabitDao): HabitDatabaseRepository {
        return HabitDatabaseRepositoryImpl(habitDao)
    }
}