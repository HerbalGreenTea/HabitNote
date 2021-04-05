package com.example.habitnote.ViewModels

import androidx.lifecycle.ViewModel
import com.example.data.Event
import com.example.data.Habit
import com.example.data.TypeHabit

class ListHabitViewModel: ViewModel() {

    private val listHabits: MutableList<Habit> = mutableListOf()

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
}

