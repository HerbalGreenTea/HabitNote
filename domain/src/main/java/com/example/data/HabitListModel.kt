package com.example.data

class HabitListModel {
    private val listHabits: MutableList<Habit> = mutableListOf()

    fun getHabits(type: TypeHabit): List<Habit> {
        return listHabits.filter { habit -> habit.type == type }
    }

    fun editHabit(newDataHabit: Habit) {
        var oldHabit: Habit? = null
        listHabits.forEach { habit ->
            if (habit.id == newDataHabit.id) oldHabit = habit
        }
        listHabits.replaceAll { habit -> if (habit == oldHabit) newDataHabit else habit}
    }

    fun createNewHabit(typeHabit: TypeHabit, eventHabit: Event<Habit>): Habit? {
        if (typeHabit == eventHabit.peekContent().type) {
            val habit = eventHabit.getContentIfNotHandled()
            if (habit != null) {
                habit.id = listHabits.size
                listHabits.add(habit)
            }
            return habit
        }
        return null
    }

    fun removeHabit(habit: Habit) {
        listHabits.remove(habit)
    }

    fun addAllHabits(habits: List<Habit>) {
        listHabits.addAll(habits)
    }
}