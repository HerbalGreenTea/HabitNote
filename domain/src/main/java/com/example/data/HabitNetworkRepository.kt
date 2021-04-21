package com.example.data

interface HabitNetworkRepository {

    suspend fun getHabits(): List<Habit>

    suspend fun putHabit(habit: Habit): Habit

    suspend fun postHabit(habit: Habit)

    suspend fun deleteHabit(uid: HabitUid)
}