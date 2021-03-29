package com.example.habitnote.ViewModels

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.data.Habit
import com.example.data.OnItemClickListener
import com.example.data.TypeHabit
import com.example.habitnote.Adapters.ListHabitAdapter
import com.example.habitnote.HabitListFragment
import com.example.habitnote.R

class ListHabitViewModel: ViewModel() {

    private val listHabits: MutableList<Habit> = mutableListOf()
    private val goodHabits: MutableList<Habit> = mutableListOf()
    private val badHabits: MutableList<Habit> = mutableListOf()

    var goodHabitsAdapter: ListHabitAdapter? = null
    var badHabitsAdapter: ListHabitAdapter? = null

    fun addHabit(habit: Habit): Habit {
        listHabits.add(habit)
        return if (habit.type == TypeHabit.GOOD) {
            habit.index = goodHabits.size
            goodHabits.add(habit)
            habit
        }
        else {
            habit.index = badHabits.size
            badHabits.add(habit)
            habit
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

    fun getHabits(): MutableList<Habit> = listHabits
    fun getGoodHabits(): MutableList<Habit> = goodHabits
    fun getBadHabits(): MutableList<Habit> = badHabits
}

