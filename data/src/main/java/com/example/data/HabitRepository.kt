package com.example.data

import androidx.lifecycle.LiveData

class HabitRepository(private val habitDao: HabitDao) {

    val readAllData: LiveData<List<Habit>> = habitDao.readAllData()

    suspend fun addHabit(habit: Habit) {
        habitDao.addHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit)
    }
}