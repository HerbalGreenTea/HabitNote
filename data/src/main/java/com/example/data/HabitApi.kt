package com.example.data

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