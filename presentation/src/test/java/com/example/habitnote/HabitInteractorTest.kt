package com.example.habitnote

import com.example.data.entities.Habit
import com.example.data.entities.PriorityHabit
import com.example.data.entities.TypeFilter
import com.example.data.entities.TypeHabit
import com.example.data.useCases.HabitDatabaseRepository
import com.example.data.useCases.HabitInteractor
import com.example.data.useCases.HabitNetworkRepository
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.junit.Assert.*

class HabitInteractorTest {

    @Test
    fun filter() {
        val mockHabitDatabaseRepository: HabitDatabaseRepository = mock()
        val mockHabitNetworkRepository: HabitNetworkRepository = mock()

        val habitInteractor = HabitInteractor(
                mockHabitDatabaseRepository,
                mockHabitNetworkRepository
        )

        val habits = listOf(
                Habit("t1", "des", PriorityHabit.HIGH, TypeHabit.GOOD, 11, 5),
                Habit("t2", "des", PriorityHabit.HIGH, TypeHabit.BAD, 10, 6),
                Habit("t3", "des", PriorityHabit.MID, TypeHabit.GOOD, 8, 3),
                Habit("t4", "des", PriorityHabit.HIGH, TypeHabit.BAD, 20, 2),
                Habit("t5", "des", PriorityHabit.LOW, TypeHabit.GOOD, 5, 10)
        )

        val goodHabit = habits.filter { it.type == TypeHabit.GOOD }

        val resHabits1 = goodHabit.filter { it.priority == PriorityHabit.LOW }
        val resHabits2 = goodHabit.filter { it.priority == PriorityHabit.MID }
        val resHabits3 = goodHabit.filter { it.priority == PriorityHabit.HIGH }
        val resHabits4 = goodHabit.sortedBy { it.count }
        val resHabits5 = goodHabit.sortedByDescending { it.count }
        val resHabits6 = goodHabit.sortedBy { it.frequency }
        val resHabits7 = goodHabit.sortedByDescending { it.frequency }

        assertEquals(resHabits1, habitInteractor.filter(habits, TypeHabit.GOOD, TypeFilter.PRIORITY_LOW))
        assertEquals(resHabits2, habitInteractor.filter(habits, TypeHabit.GOOD, TypeFilter.PRIORITY_MID))
        assertEquals(resHabits3, habitInteractor.filter(habits, TypeHabit.GOOD, TypeFilter.PRIORITY_HIGH))
        assertEquals(resHabits4, habitInteractor.filter(habits, TypeHabit.GOOD, TypeFilter.SORT_INCREASE_COUNT))
        assertEquals(resHabits5, habitInteractor.filter(habits, TypeHabit.GOOD, TypeFilter.SORT_DECREASE_COUNT))
        assertEquals(resHabits6, habitInteractor.filter(habits, TypeHabit.GOOD, TypeFilter.SORT_INCREASE_FREQUENCY))
        assertEquals(resHabits7, habitInteractor.filter(habits, TypeHabit.GOOD, TypeFilter.SORT_DECREASE_FREQUENCY))
    }
}