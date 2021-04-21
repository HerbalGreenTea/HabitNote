package com.example.data

import kotlinx.coroutines.flow.Flow

class HabitNetworkRepositoryImpl(private val habitApi: HabitApi): HabitNetworkRepository {

    override suspend fun getHabits(): List<Habit> = habitApi.getHabits()

    override suspend fun putHabit(habit: Habit): Habit = habitApi.putHabit(habit)

    override suspend fun postHabit(habit: Habit) = habitApi.postHabit(habit)

    override suspend fun deleteHabit(uid: HabitUid) = habitApi.deleteHabit(uid)
}