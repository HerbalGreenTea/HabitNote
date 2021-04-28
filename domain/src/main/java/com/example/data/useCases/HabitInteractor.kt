package com.example.data.useCases

import com.example.data.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.*

class HabitInteractor(
    private val databaseHabits: HabitDatabaseRepository,
    private val habitNetworkRepository: HabitNetworkRepository
) {
    companion object {
        private const val TIMEOUT = 1000L
    }

    val readAllData: Flow<List<Habit>> = databaseHabits.readAllData()

    suspend fun loadData() {
        performSuccess(
            { habitNetworkRepository.getHabits() },
            { habits -> habits.forEach{ databaseHabits.addHabit(it) } }
        )
    }

    suspend fun addHabit(habit: Habit) {
        performSuccess(
            { habitNetworkRepository.putHabit(habit) },
            {
                val prevUid = habit.id
                habit.id = it.id
                if (prevUid.uid == null) databaseHabits.addHabit(habit)
                else databaseHabits.updateHabit(habit)
            }
        )
    }

    suspend fun updateHabit(habit: Habit) {
        if (habit.id.uid != null) {
            habitNetworkRepository.putHabit(habit)
            databaseHabits.updateHabit(habit)
        }
    }

    suspend fun deleteHabit(habit: Habit) {
        if (habit.id.uid != null) {
            habitNetworkRepository.deleteHabit(habit.id)
            databaseHabits.deleteHabit(habit)
        }
    }

    private suspend fun sendDoneHabit(habit: Habit, frequency: Int) {
        if (habit.id.uid != null) {
            val doneHabit = DoneHabit(habit.id.uid as String, GregorianCalendar().time.time)

            habit.doneDates =
                    if (frequency >= 0)
                        mutableListOf(doneHabit.date).apply { addAll(habit.doneDates) }
                    else
                        listOf()

            habitNetworkRepository.postHabit(doneHabit)
            databaseHabits.updateHabit(habit)
        }
    }

    suspend fun doneHabit(habit: Habit,
                     funShowMessage1: (habit: Habit) -> Unit,
                     funShowMessage2: (habit: Habit) -> Unit) {

        val firstDate = GregorianCalendar().apply {
            time = if (habit.doneDates.isNotEmpty()) Date(habit.doneDates.first())
            else Date(habit.date)
        }
        val dayFirst = firstDate.get(Calendar.DAY_OF_YEAR)
        val dayLast = GregorianCalendar().get(Calendar.DAY_OF_YEAR)

        val frequency = habit.frequency - Math.abs(dayLast - dayFirst)

        sendDoneHabit(habit, frequency)

        withContext(Dispatchers.Main) {
            if (frequency >= 0) funShowMessage1(habit)
            else funShowMessage2(habit)
        }
    }

    fun filter(habits: List<Habit>, type: TypeHabit, typeFilter: TypeFilter): List<Habit>{
        val filterHabits = habits.filter { it.type == type }

        return when(typeFilter) {
            TypeFilter.PRIORITY_LOW -> filterHabits.filter { it.priority == PriorityHabit.LOW }
            TypeFilter.PRIORITY_MID -> filterHabits.filter { it.priority == PriorityHabit.MID }
            TypeFilter.PRIORITY_HIGH -> filterHabits.filter { it.priority == PriorityHabit.HIGH }
            TypeFilter.SORT_INCREASE_COUNT -> filterHabits.sortedBy { it.count }
            TypeFilter.SORT_DECREASE_COUNT -> filterHabits.sortedByDescending { it.count }
            TypeFilter.SORT_INCREASE_FREQUENCY -> filterHabits.sortedBy { it.frequency }
            TypeFilter.SORT_DECREASE_FREQUENCY -> filterHabits.sortedByDescending { it.frequency }
            TypeFilter.NONE -> filterHabits
        }
    }

    private suspend fun <T> performSuccess(
        responseGetter: suspend () -> Response<T>,
        callback: suspend (T) -> Unit
    ) {
        while (true) {
            try {
                val response = responseGetter()
                if (response.isSuccessful) {
                    response.body()?.let { callback(it) }
                    break
                } else {
                    if (response.code() >= 500) {
                        delay(TIMEOUT)
                    } else {
                        break
                    }
                }
            } catch (e: Exception) {
                print(e)
                break
            }
        }
    }
}