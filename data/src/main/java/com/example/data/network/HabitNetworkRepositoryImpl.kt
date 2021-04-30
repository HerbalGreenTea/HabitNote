package com.example.data.network

import com.example.data.entities.DoneHabit
import com.example.data.entities.Habit
import com.example.data.entities.HabitUid
import com.example.data.useCases.HabitInteractor
import com.example.data.useCases.HabitNetworkRepository
import kotlinx.coroutines.delay
import retrofit2.Response

class HabitNetworkRepositoryImpl(private val habitApi: HabitApi): HabitNetworkRepository {
    companion object {
        private const val TIMEOUT = 1000L
    }

    override suspend fun getHabits(): List<Habit>? = performSuccess { habitApi.getHabits() }

    override suspend fun putHabit(habit: Habit): Habit? = performSuccess { habitApi.putHabit(habit) }

    override suspend fun postHabit(doneHabit: DoneHabit) = habitApi.postHabit(doneHabit)

    override suspend fun deleteHabit(uid: HabitUid) = habitApi.deleteHabit(uid)

    private suspend fun <T> performSuccess(responseGetter: suspend () -> Response<T>): T? {
        while (true) {
            try {
                val response = responseGetter()
                when {
                    (response.isSuccessful) -> return response.body()
                    (response.code() >= 500) -> delay(TIMEOUT)
                    else -> return null
                }
            } catch (e: Exception) {
                print(e)
                return null
            }
        }
    }
}