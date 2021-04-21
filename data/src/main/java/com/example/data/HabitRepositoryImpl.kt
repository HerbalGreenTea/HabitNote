package com.example.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class HabitRepositoryImpl(private val habitDao: HabitDao): HabitRepository {

    override fun readAllData(): Flow<List<Habit>> {
        return habitDao.readAllData()
    }

    override suspend fun getAllHabit(): List<Habit> {
        return habitDao.getHabits()
    }

    override suspend fun addHabit(habit: Habit) {
        habitDao.addHabit(habit)
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit)
    }
}