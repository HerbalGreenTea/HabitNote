package com.example.data.useCases

import com.example.data.entities.Habit
import com.example.data.entities.HabitUid
import com.example.data.entities.PriorityHabit
import com.example.data.entities.TypeFilter
import com.example.data.entities.TypeHabit
import kotlinx.coroutines.flow.Flow

class HabitInteractor(
    private val databaseHabits: HabitDatabaseRepository,
    private val habitNetworkRepository: HabitNetworkRepository
) {

    val readAllData: Flow<List<Habit>> = databaseHabits.readAllData()

    suspend fun loadData() {
        val habits: List<Habit> = habitNetworkRepository.getHabits()
        val habitsUid: List<HabitUid?> = databaseHabits.getAllHabit().map { habit -> habit.uid }

        habits.forEach {
            if (!habitsUid.contains(it.uid)) {
                databaseHabits.addHabit(it)
            }
        }
    }

    suspend fun addHabit(habit: Habit) {
        val habitUid = habitNetworkRepository.putHabit(habit).uid
        habit.uid = habitUid
        if (habit.uid != null)
            databaseHabits.addHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        if (habit.uid != null)
            habitNetworkRepository.deleteHabit(habit.uid as HabitUid)
        val habitUid = habitNetworkRepository.putHabit(habit).uid
        habit.uid = habitUid
        databaseHabits.updateHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        if (habit.uid != null)
            habitNetworkRepository.deleteHabit(habit.uid as HabitUid)
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