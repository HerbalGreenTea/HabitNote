package com.example.habitnote.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.Event
import com.example.data.Habit
import com.example.data.TypeFilter

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

    private val mutableRemoveHabit: MutableLiveData<Event<Habit>> = MutableLiveData()
    val removeHabit: LiveData<Event<Habit>> = mutableRemoveHabit

    fun setValueRemoveHabit(event: Event<Habit>) {
        mutableRemoveHabit.value = event
    }
}