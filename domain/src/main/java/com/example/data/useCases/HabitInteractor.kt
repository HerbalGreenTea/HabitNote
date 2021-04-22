package com.example.data.useCases

import com.example.data.entities.*
import kotlinx.coroutines.flow.Flow

class HabitInteractor(
    private val databaseHabits: HabitDatabaseRepository,
    private val habitNetworkRepository: HabitNetworkRepository
) {

    val readAllData: Flow<List<Habit>> = databaseHabits.readAllData()

    suspend fun loadData() {
        habitNetworkRepository
                .getHabits()
                .forEach { databaseHabits.addHabit(it) }
    }

    suspend fun addHabit(habit: Habit) {
        val habitUid = habitNetworkRepository.putHabit(habit).id
        habit.id = habitUid
        if (habit.id.uid != null)
            databaseHabits.addHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        if (habit.id.uid != null) {
            habitNetworkRepository.putHabit(habit)
            databaseHabits.updateHabit(habit)
        }
    }

    suspend fun deleteHabit(habit: Habit) {
        if (habit.id.uid != null) {
            habitNetworkRepository.deleteHabit(habit.id)
            databaseHabits.deleteHabit(habit)
        }
    }

    suspend fun doneHabit(doneHabit: DoneHabit) {
        habitNetworkRepository.postHabit(doneHabit)
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