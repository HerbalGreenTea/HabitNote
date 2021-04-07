package com.example.habitnote.ViewModels

import androidx.lifecycle.ViewModel
import com.example.data.*

class ListHabitViewModel: ViewModel() {

    private val listHabits: MutableList<Habit> = mutableListOf(
            Habit(1, "test1", "des", PriorityHabit.MID, TypeHabit.GOOD, 100, 100, 999999999),
            Habit(2, "test2", "des", PriorityHabit.HIGH, TypeHabit.BAD, 300, 300, 999999999),
            Habit(3, "test3", "des", PriorityHabit.LOW, TypeHabit.BAD, 5, 5, 999999999),
            Habit(4, "test4", "des", PriorityHabit.LOW, TypeHabit.GOOD, 700, 500, 999999999),
            Habit(5, "test5", "des", PriorityHabit.HIGH, TypeHabit.GOOD, 1, 1, 999999999),
    )

    fun getHabits(type: TypeHabit): List<Habit> {
        return listHabits.filter { habit -> habit.type == type }
    }

    private fun updateHabit(newDataHabit: Habit) {
        var oldHabit: Habit? = null
        listHabits.forEach { habit ->
            if (habit.id == newDataHabit.id) oldHabit = habit
        }

        listHabits.replaceAll { habit -> if (habit == oldHabit) newDataHabit else habit}
    }

    fun createNewHabit(typeHabit: TypeHabit, eventHabit: Event<Habit>) {
        if (typeHabit == eventHabit.peekContent().type) {
            val habit = eventHabit.getContentIfNotHandled()
            if (habit != null) {
                habit.id = listHabits.size
                listHabits.add(habit)
            }
        }
    }

    fun editHabit(typeHabit: TypeHabit, eventHabit: Event<Habit>) {
        if (typeHabit == eventHabit.peekContent().type) {
            val habit = eventHabit.getContentIfNotHandled()
            if (habit != null) { updateHabit(habit) }
        }
    }

    fun switchTypeHabit(typeHabit: TypeHabit, eventHabit: Event<Habit>) {
        if (typeHabit == eventHabit.peekContent().type) {
            val habit = eventHabit.getContentIfNotHandled()
            if (habit != null) { listHabits.remove(habit) }
        }
    }

    fun filter(type: TypeHabit, typeFilter: TypeFilter): List<Habit>{
        val habits = getHabits(type)

        return when(typeFilter) {
            TypeFilter.PRIORITY_LOW -> habits.filter { it.priority == PriorityHabit.LOW }
            TypeFilter.PRIORITY_MID -> habits.filter { it.priority == PriorityHabit.MID }
            TypeFilter.PRIORITY_HIGH -> habits.filter { it.priority == PriorityHabit.HIGH }
            TypeFilter.SORT_INCREASE_COUNT -> habits.sortedBy { it.count }
            TypeFilter.SORT_DECREASE_COUNT -> habits.sortedByDescending { it.count }
            TypeFilter.NONE -> habits
        }
    }
}

