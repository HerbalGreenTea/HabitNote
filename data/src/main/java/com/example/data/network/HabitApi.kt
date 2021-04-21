package com.example.data.network

import com.example.data.entities.Habit
import com.example.data.entities.HabitUid
import retrofit2.http.*

interface HabitApi {

    @GET("habit")
    suspend fun getHabits(): List<Habit>

    @PUT("habit")
    suspend fun putHabit(@Body habit: Habit): Habit

    @POST("habit")
    suspend fun postHabit(@Body habit: Habit)

    @HTTP(method = "DELETE", path = "habit", hasBody = true)
    suspend fun deleteHabit(@Body uid: HabitUid)
}