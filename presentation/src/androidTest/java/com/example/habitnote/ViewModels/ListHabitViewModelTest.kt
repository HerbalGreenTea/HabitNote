package com.example.habitnote.ViewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.entities.TypeFilter
import com.example.data.useCases.HabitDatabaseRepository
import com.example.data.useCases.HabitInteractor
import com.example.data.useCases.HabitNetworkRepository
import com.example.habitnote.MainActivity
import junit.framework.TestCase
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class ListHabitViewModelTest: TestCase() {

    private lateinit var listHabitViewModel: ListHabitViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        // FIXME: 06.05.21 мокать интерактор
        // FIXME: 06.05.21 перенести в обычный test

        val mock1 = mock(HabitNetworkRepository::class.java)
        val mock2 = mock(HabitDatabaseRepository::class.java)
        Mockito.`when`(mock2.readAllData()).thenReturn(flowOf())
        val habitInteractor = HabitInteractor(mock2, mock1)

        listHabitViewModel = ListHabitViewModel(habitInteractor)
    }

    @Test
    fun actionFilter() {
        listHabitViewModel.setValueActionFilter(TypeFilter.PRIORITY_MID)
        assertEquals(TypeFilter.PRIORITY_MID, listHabitViewModel.actionFilter.value)
    }
}