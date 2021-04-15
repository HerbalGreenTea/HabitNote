package com.example.habitnote.ViewModels

import androidx.lifecycle.*
import com.example.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListHabitViewModel(
    private val model: HabitListModel,
    private val databaseHabits: HabitRepository
    ): ViewModel() {

    val readAllData: LiveData<List<Habit>> = databaseHabits.readAllData

    fun addHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseHabits.addHabit(habit)
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseHabits.deleteHabit(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseHabits.updateHabit(habit)
        }
    }

    var currentTypeFilter: TypeFilter = TypeFilter.NONE
    private val mutableActionFilter: MutableLiveData<TypeFilter> = MutableLiveData()
    val actionFilter: LiveData<TypeFilter> = mutableActionFilter

    fun setValueActionFilter(typeFilter: TypeFilter) {
        mutableActionFilter.value = typeFilter
    }

    fun getHabits(type: TypeHabit): List<Habit> {
        return model.getHabits(type, currentTypeFilter)
    }

    fun loadHabits(habits: List<Habit>) {
        model.loadHabits(habits)
    }
}

