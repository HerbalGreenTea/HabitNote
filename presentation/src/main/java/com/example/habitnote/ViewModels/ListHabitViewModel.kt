package com.example.habitnote.ViewModels

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.example.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListHabitViewModel(
    private val model: HabitListModel,
    private val databaseHabits: HabitRepository
    ): ViewModel() {

    val readAllData: LiveData<List<Habit>> = databaseHabits.readAllData

    private fun addHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseHabits.addHabit(habit)
        }
    }

    private fun deleteHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseHabits.deleteHabit(habit)
        }
    }

    private fun updateHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseHabits.updateHabit(habit)
        }
    }

    fun addAllHabits(habits: List<Habit>) {
        model.addAllHabits(habits)
    }

    var isLoadData = true

    ////////////////////////////////////////////////////////////////////////////////////

    private val mutableActionFilter: MutableLiveData<TypeFilter> = MutableLiveData()
    val actionFilter: LiveData<TypeFilter> = mutableActionFilter

    fun setValueActionFilter(typeFilter: TypeFilter) {
        mutableActionFilter.value = typeFilter
    }

    fun getHabits(type: TypeHabit): List<Habit> {
        return model.getHabits(type)
    }

    fun createNewHabit(typeHabit: TypeHabit, eventHabit: Event<Habit>) {
        val habit = model.createNewHabit(typeHabit, eventHabit)
        if (habit != null)
            addHabit(habit)
    }

    fun editHabit(eventHabit: Event<Habit>) {
        val habit = eventHabit.getContentIfNotHandled()
        if (habit != null) {
            updateHabit(habit)
            model.editHabit(habit)
        }
    }

    fun removeHabit(habit: Habit) {
        deleteHabit(habit)
        model.removeHabit(habit)
    }

    fun filter(type: TypeHabit, typeFilter: TypeFilter): List<Habit>{
        val habits = getHabits(type)

        return when(typeFilter) {
            TypeFilter.PRIORITY_LOW -> habits.filter { it.priority == PriorityHabit.LOW }
            TypeFilter.PRIORITY_MID -> habits.filter { it.priority == PriorityHabit.MID }
            TypeFilter.PRIORITY_HIGH -> habits.filter { it.priority == PriorityHabit.HIGH }
            TypeFilter.SORT_INCREASE_COUNT -> habits.sortedBy { it.count }
            TypeFilter.SORT_DECREASE_COUNT -> habits.sortedByDescending { it.count }
            TypeFilter.SORT_INCREASE_FREQUENCY -> habits.sortedBy { it.frequency }
            TypeFilter.SORT_DECREASE_FREQUENCY -> habits.sortedByDescending { it.frequency }
            TypeFilter.NONE -> habits
        }
    }
}

