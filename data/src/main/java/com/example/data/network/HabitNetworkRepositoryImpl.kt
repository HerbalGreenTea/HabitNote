package com.example.data.network

import com.example.data.entities.Habit
import com.example.data.entities.HabitUid
import com.example.data.useCases.HabitNetworkRepository

class HabitNetworkRepositoryImpl(private val habitApi: HabitApi): HabitNetworkRepository {

    override suspend fun getHabits(): List<Habit> = habitApi.getHabits()

    override suspend fun putHabit(habit: Habit): Habit = habitApi.putHabit(habit)

    override suspend fun postHabit(habit: Habit) = habitApi.postHabit(habit)

    override suspend fun deleteHabit(uid: HabitUid) = habitApi.deleteHabit(uid)
}