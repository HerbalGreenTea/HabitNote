package com.example.data

import kotlinx.coroutines.flow.Flow

interface HabitRepository {
    fun readAllData(): Flow<List<Habit>>

    suspend fun addHabit(habit: Habit)

    suspend fun updateHabit(habit: Habit)

    suspend fun deleteHabit(habit: Habit)
}