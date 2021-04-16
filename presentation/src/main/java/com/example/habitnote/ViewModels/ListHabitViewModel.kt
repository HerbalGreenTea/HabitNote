package com.example.habitnote.ViewModels

import androidx.lifecycle.*
import com.example.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListHabitViewModel(
    private val databaseHabits: HabitRepository
    ): ViewModel() {

    val readAllData: LiveData<List<Habit>> = databaseHabits.readAllData

    private val mutableActionFilter: MutableLiveData<TypeFilter> = MutableLiveData()
    val actionFilter: LiveData<TypeFilter> = mutableActionFilter

    fun setValueActionFilter(typeFilter: TypeFilter) {
        mutableActionFilter.value = typeFilter
    }

    fun addHabit(eventHabit: Event<Habit>) {
        val habit = eventHabit.getContentIfNotHandled()
        if (habit != null) {
            viewModelScope.launch(Dispatchers.IO) {
                databaseHabits.addHabit(habit)
            }
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseHabits.deleteHabit(habit)
        }
    }

    fun updateHabit(eventHabit: Event<Habit>) {
        val habit = eventHabit.getContentIfNotHandled()
        if (habit != null) {
            viewModelScope.launch(Dispatchers.IO) {
                databaseHabits.updateHabit(habit)
            }
        }
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

