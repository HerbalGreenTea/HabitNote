package com.example.habitnote.ViewModels

import androidx.lifecycle.*
import com.example.data.entities.DoneHabit
import com.example.data.entities.Habit
import com.example.data.entities.TypeFilter
import com.example.data.entities.TypeHabit
import com.example.data.useCases.Event
import com.example.data.useCases.HabitInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListHabitViewModel @Inject constructor(
    private val habitInteractor: HabitInteractor
    ): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            habitInteractor.loadData()
        }
    }

    val readAllData: LiveData<List<Habit>> = habitInteractor.readAllData.asLiveData()

    private val mutableActionFilter: MutableLiveData<TypeFilter> = MutableLiveData()
    val actionFilter: LiveData<TypeFilter> = mutableActionFilter

    fun setValueActionFilter(typeFilter: TypeFilter) {
        mutableActionFilter.value = typeFilter
    }

    fun addHabit(eventHabit: Event<Habit>) {
        val habit = eventHabit.getContentIfNotHandled()
        if (habit != null) {
            viewModelScope.launch(Dispatchers.IO) {
                habitInteractor.addHabit(habit)
            }
        }
    }

    fun deleteHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitInteractor.deleteHabit(habit)
        }
    }

    fun updateHabit(eventHabit: Event<Habit>) {
        val habit = eventHabit.getContentIfNotHandled()
        if (habit != null) {
            viewModelScope.launch(Dispatchers.IO) {
                habitInteractor.updateHabit(habit)
            }
        }
    }

    fun doneHabit(doneHabit: DoneHabit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitInteractor.doneHabit(doneHabit)
        }
    }

    fun applyFilter(habits: List<Habit>, type: TypeHabit, typeFilter: TypeFilter): List<Habit> {
        return habitInteractor.filter(habits, type, typeFilter)
    }
}

