package com.example.data

import kotlinx.coroutines.flow.Flow

class HabitInteractor(
    private val databaseHabits: HabitRepository
) {

    val readAllData: Flow<List<Habit>> = databaseHabits.readAllData()

    suspend fun addHabit(habit: Habit) {
        databaseHabits.addHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        databaseHabits.updateHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        databaseHabits.deleteHabit(habit)
    }

    fun filter(habits: List<Habit>, type: TypeHabit, typeFilter: TypeFilter): List<Habit>{
        val filterHabits = habits.filter { it.type == type }

        return when(typeFilter) {
            TypeFilter.PRIORITY_LOW -> filterHabits.filter { it.priority == PriorityHabit.LOW }
            TypeFilter.PRIORITY_MID -> filterHabits.filter { it.priority == PriorityHabit.MID }
            TypeFilter.PRIORITY_HIGH -> filterHabits.filter { it.priority == PriorityHabit.HIGH }
            TypeFilter.SORT_INCREASE_COUNT -> filterHabits.sortedBy { it.count }
            TypeFilter.SORT_DECREASE_COUNT -> filterHabits.sortedByDescending { it.count }
            TypeFilter.SORT_INCREASE_FREQUENCY -> filterHabits.sortedBy { it.frequency }
            TypeFilter.SORT_DECREASE_FREQUENCY -> filterHabits.sortedByDescending { it.frequency }
            TypeFilter.NONE -> filterHabits
        }
    }
}