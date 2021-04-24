package com.example.data.network

import com.example.data.entities.DoneHabit
import com.example.data.entities.Habit
import com.example.data.entities.HabitUid
import retrofit2.Response
import retrofit2.http.*

interface HabitApi {

    @GET("habit")
    suspend fun getHabits(): Response<List<Habit>>

    @PUT("habit")
    suspend fun putHabit(@Body habit: Habit): Response<Habit>

    @POST("habit_done")
    suspend fun postHabit(@Body doneHabit: DoneHabit)

    @HTTP(method = "DELETE", path = "habit", hasBody = true)
    suspend fun deleteHabit(@Body uid: HabitUid)
}