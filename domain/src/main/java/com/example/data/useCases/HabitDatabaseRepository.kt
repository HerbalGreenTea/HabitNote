package com.example.data.useCases

import com.example.data.entities.Habit
import kotlinx.coroutines.flow.Flow

interface HabitDatabaseRepository {
    fun readAllData(): Flow<List<Habit>>

    suspend fun addHabit(habit: Habit)

    suspend fun updateHabit(habit: Habit)

    suspend fun deleteHabit(habit: Habit)
}