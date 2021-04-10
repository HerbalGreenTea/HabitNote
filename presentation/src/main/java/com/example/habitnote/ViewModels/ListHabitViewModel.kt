package com.example.habitnote.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.*

class ListHabitViewModel(
        private val model: HabitListModel
        ): ViewModel() {

    private val mutableActionFilter: MutableLiveData<TypeFilter> = MutableLiveData()
    val actionFilter: LiveData<TypeFilter> = mutableActionFilter


    fun getHabits(type: TypeHabit): List<Habit> {
        return model.getHabits(type)
    }

    fun createNewHabit(typeHabit: TypeHabit, eventHabit: Event<Habit>) {
        model.createNewHabit(typeHabit, eventHabit)
    }

    fun editHabit(typeHabit: TypeHabit, eventHabit: Event<Habit>) {
        model.editHabit(typeHabit, eventHabit)
    }

    fun switchTypeHabit(typeHabit: TypeHabit, eventHabit: Event<Habit>) {
        model.switchTypeHabit(typeHabit, eventHabit)
    }

    fun setValueActionFilter(typeFilter: TypeFilter) {
        mutableActionFilter.value = typeFilter
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

