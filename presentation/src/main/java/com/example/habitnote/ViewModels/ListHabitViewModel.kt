package com.example.habitnote.ViewModels

import androidx.lifecycle.ViewModel
import com.example.data.Habit
import com.example.data.TypeHabit

class ListHabitViewModel: ViewModel() {

    private val listHabits: MutableList<Habit> = mutableListOf()

    private val goodHabits: MutableList<Habit> = mutableListOf()
    private val badHabits: MutableList<Habit> = mutableListOf()

    fun addHabit(habit: Habit): Habit {
        listHabits.add(habit)
        if (habit.type == TypeHabit.GOOD) {
            habit.index = goodHabits.size
            goodHabits.add(habit)
            return habit
        }
        else {
            habit.index = badHabits.size
            badHabits.add(habit)
            return habit
        }
    }

    fun removeHabit(habit: Habit) {
        listHabits.remove(habit)
        if (habit.type == TypeHabit.GOOD) {
            goodHabits.remove(habit)
        } else {
            badHabits.remove(habit)
        }
    }

    fun getGoodHabits(): MutableList<Habit> = goodHabits
    fun getBadHabits(): MutableList<Habit> = badHabits
}