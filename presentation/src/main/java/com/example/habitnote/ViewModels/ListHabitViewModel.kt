package com.example.habitnote.ViewModels

import androidx.lifecycle.*
import com.example.data.entities.Habit
import com.example.data.entities.TypeFilter
import com.example.data.entities.TypeHabit
import com.example.data.useCases.Event
import com.example.data.useCases.HabitInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListHabitViewModel @Inject constructor(
        private val habitInteractor: HabitInteractor): ViewModel() {

    var readAllDataHabits: LiveData<List<Habit>> = habitInteractor.readAllDataHabits().asLiveData()

    init {
        viewModelScope.launch { habitInteractor.loadData() }
    }

    private val mutableActionFilter: MutableLiveData<TypeFilter> = MutableLiveData()
    val actionFilter: LiveData<TypeFilter> = mutableActionFilter

    fun setValueActionFilter(typeFilter: TypeFilter) {
        mutableActionFilter.value = typeFilter
    }

    fun addHabit(eventHabit: Event<Habit>) {
        val habit = eventHabit.getContentIfNotHandled()
        if (habit != null) {
            viewModelScope.launch { habitInteractor.addHabit(habit) }
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch { habitInteractor.deleteHabit(habit) }
    }

    fun updateHabit(eventHabit: Event<Habit>) {
        val habit = eventHabit.getContentIfNotHandled()
        if (habit != null) {
            viewModelScope.launch { habitInteractor.updateHabit(habit) }
        }
    }

    fun doneHabit(habit: Habit,
                  showMessage1: () -> Unit,
                  showMessage2: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val frequency = habitInteractor.doneHabit(habit)

            withContext(Dispatchers.Main) {
                if (frequency >= 0) showMessage1()
                else showMessage2()
            }
        }
    }

    fun applyFilter(habits: List<Habit>, type: TypeHabit, typeFilter: TypeFilter): List<Habit> {
        return habitInteractor.filter(habits, type, typeFilter)
    }
}

