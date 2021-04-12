package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.Serializable

@Entity(tableName = "habit_table")
@TypeConverters(HabitConverterPriority::class, HabitConverterType::class)
data class Habit (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title: String,
    var description: String,
    var priority: PriorityHabit,
    var type: TypeHabit,
    var frequency: Int,
    var count: Int,
    var color: Int): Serializable

class HabitConverterType {

    @TypeConverter
    fun fromTypeHabit(type: TypeHabit): Int {
        return type.typeCode
    }

    @TypeConverter
    fun toTypeHabit(code: Int): TypeHabit {
        return  TypeHabit.getTypeAtCode(code)
    }
}

class HabitConverterPriority {
    @TypeConverter
    fun fromTypePriority(type: PriorityHabit): Int {
        return type.priorityCode
    }

    @TypeConverter
    fun toTypePriority(code: Int): PriorityHabit {
        return PriorityHabit.getPriorityAtCode(code)
    }
}