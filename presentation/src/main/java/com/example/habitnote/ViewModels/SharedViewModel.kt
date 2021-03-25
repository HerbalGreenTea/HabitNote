package com.example.habitnote.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.Habit

class SharedViewModel: ViewModel() {
    val createNewHabit: MutableLiveData<Habit> by lazy {
        MutableLiveData<Habit>()
    }

    val editHabit: MutableLiveData<Habit> by lazy {
        MutableLiveData<Habit>()
    }

    val removeHabit: MutableLiveData<Habit> by lazy {
        MutableLiveData<Habit>()
    }
}