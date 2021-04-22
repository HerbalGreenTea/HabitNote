package com.example.data.database

import com.example.data.entities.Habit
import com.example.data.useCases.HabitDatabaseRepository
import kotlinx.coroutines.flow.Flow

class HabitDatabaseRepositoryImpl(private val habitDao: HabitDao): HabitDatabaseRepository {

    override fun readAllData(): Flow<List<Habit>> = habitDao.readAllData()

    override suspend fun addHabit(habit: Habit) = habitDao.addHabit(habit)

    override suspend fun updateHabit(habit: Habit) = habitDao.updateHabit(habit)

    override suspend fun deleteHabit(habit: Habit) = habitDao.deleteHabit(habit)
}