package com.example.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.typeConverters.*
import java.io.Serializable

@Entity(tableName = "habit_table")
@TypeConverters(
        HabitConverterPriority::class,
        HabitConverterType::class,
        HabitConverterUid::class,
        HabitConverterDoneDates::class)
data class Habit (
    var title: String = "",
    var description: String = "",
    var priority: PriorityHabit = PriorityHabit.LOW,
    var type: TypeHabit = TypeHabit.GOOD,
    var frequency: Int = 0,
    var count: Int = 0,
    var color: Int = 0,
    var date: Long = 0,
    var doneDates: List<Long> = listOf()
): Serializable {
    @PrimaryKey
    var id: HabitUid = HabitUid(null)
}