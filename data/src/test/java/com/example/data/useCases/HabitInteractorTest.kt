package com.example.data.useCases

import com.example.data.entities.Habit
import com.example.data.entities.PriorityHabit
import com.example.data.entities.TypeFilter
import com.example.data.entities.TypeHabit
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*

class HabitInteractorTest {

    @Test
    fun loadData() {

    }

    @Test
    fun addHabit() {

    }

    @Test
    fun updateHabit() {

    }

    @Test
    fun deleteHabit() {

    }

    @Test
    fun doneHabit() {

    }

    @Test
    fun filter() {
        val habits = listOf(
            Habit("h1", "", PriorityHabit.HIGH, TypeHabit.GOOD, 10, 1),
            Habit("h2", "", PriorityHabit.HIGH, TypeHabit.BAD, 8, 5),
            Habit("h3", "", PriorityHabit.MID, TypeHabit.GOOD, 19, 3),
            Habit("h4", "", PriorityHabit.LOW, TypeHabit.GOOD, 15, 8)
        )

        val mockHabitDatabaseRepository: HabitDatabaseRepository = mock()
        val mockHabitNetworkRepository: HabitNetworkRepository = mock()

        val habitInteractor = HabitInteractor(mockHabitDatabaseRepository, mockHabitNetworkRepository)

        val resHabits = habitInteractor.filter(habits, TypeHabit.GOOD, TypeFilter.PRIORITY_HIGH)
        val requiredResult = listOf(
            Habit("h1", "", PriorityHabit.HIGH, TypeHabit.GOOD, 10, 1))

        assertEquals(requiredResult.size, resHabits.size)
        assertEquals(requiredResult.first().title, resHabits.first().title)
    }
}