package com.example.habitnote.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.Event
import com.example.data.Habit
import com.example.data.TypeFilter

class SharedViewModel: ViewModel() {
    val createNewHabit: MutableLiveData<Event<Habit>> by lazy {
        MutableLiveData<Event<Habit>>()
    }

    val editHabit: MutableLiveData<Event<Habit>> by lazy {
        MutableLiveData<Event<Habit>>()
    }

    val removeHabit: MutableLiveData<Event<Habit>> by lazy {
        MutableLiveData<Event<Habit>>()
    }

    val actionFilter: MutableLiveData<Event<TypeFilter>> by lazy {
        MutableLiveData()
    }
}