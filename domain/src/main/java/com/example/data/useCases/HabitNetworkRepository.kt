package com.example.data.useCases

import com.example.data.entities.DoneHabit
import com.example.data.entities.Habit
import com.example.data.entities.HabitUid
import retrofit2.Response

interface HabitNetworkRepository {

    suspend fun getHabits(): List<Habit>?

    suspend fun putHabit(habit: Habit): Habit?

    suspend fun postHabit(doneHabit: DoneHabit)

    suspend fun deleteHabit(uid: HabitUid)
}