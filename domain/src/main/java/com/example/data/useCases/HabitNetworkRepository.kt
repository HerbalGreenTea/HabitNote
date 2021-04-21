package com.example.data.useCases

import com.example.data.entities.Habit
import com.example.data.entities.HabitUid

interface HabitNetworkRepository {

    suspend fun getHabits(): List<Habit>

    suspend fun putHabit(habit: Habit): Habit

    suspend fun postHabit(habit: Habit)

    suspend fun deleteHabit(uid: HabitUid)
}