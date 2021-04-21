package com.example.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("SELECT * FROM habit_table ORDER BY id ASC")
    suspend fun getHabits(): List<Habit>

    @Query("SELECT * FROM habit_table ORDER BY id ASC")
    fun readAllData(): Flow<List<Habit>>
}