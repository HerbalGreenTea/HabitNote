package com.example.habitnote.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.useCases.Event
import com.example.data.entities.Habit

class SharedViewModel: ViewModel() {
    private val mutableCreateHabit: MutableLiveData<Event<Habit>> = MutableLiveData()
    val createNewHabit: LiveData<Event<Habit>> = mutableCreateHabit

    fun setValueCreateHabit(event: Event<Habit>) {
        mutableCreateHabit.value = event
    }

    private val mutableEditHabit: MutableLiveData<Event<Habit>> = MutableLiveData()
    val editHabit: LiveData<Event<Habit>> = mutableEditHabit

    fun setValueEditHabit(event: Event<Habit>) {
        mutableEditHabit.value = event
    }
}