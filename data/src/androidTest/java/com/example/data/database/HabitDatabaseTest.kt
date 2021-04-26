package com.example.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.entities.Habit
import com.example.data.entities.HabitUid
import com.example.data.entities.PriorityHabit
import com.example.data.entities.TypeHabit
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HabitDatabaseTest: TestCase() {

    private lateinit var db: HabitDatabase
    private lateinit var dao: HabitDao


    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, HabitDatabase::class.java).build()
        dao = db.habitDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun addHabit() = runBlocking {
        val habit = Habit("h1", "test", PriorityHabit.MID, TypeHabit.GOOD)
        habit.id = HabitUid("uid1")

        dao.addHabit(habit)
        val habits = dao.readAllData().first()

        assertThat(habits.contains(habit)).isTrue()
        habit.title = "h2"
        assertThat(habits.contains(habit)).isFalse()

        assertEquals(1, habits.size)
    }

    @Test
    fun deleteHabit() = runBlocking {
        val habit = Habit("h1", "test", PriorityHabit.MID, TypeHabit.GOOD)
        habit.id = HabitUid("uid1")

        dao.addHabit(habit)
        dao.deleteHabit(habit)

        val habits = dao.readAllData().first()

        assertThat(habits.isEmpty()).isTrue()
    }

    @Test
    fun updateTest() = runBlocking {
        val oldHabit = Habit("h1", "test", PriorityHabit.MID, TypeHabit.GOOD).apply {
            id = HabitUid("uid1")
        }
        val newHabit = Habit("h2", "test", PriorityHabit.MID, TypeHabit.GOOD).apply {
            id = HabitUid("uid1")
        }

        dao.addHabit(oldHabit)
        dao.updateHabit(newHabit)

        val habits = dao.readAllData().first()

        assertThat(habits.contains(newHabit)).isTrue()
        assertThat(habits.contains(oldHabit)).isFalse()
    }
}











