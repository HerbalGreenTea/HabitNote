package com.example.data.typeConverters

import androidx.room.TypeConverter
import com.example.data.entities.PriorityHabit

class HabitConverterPriority {
    @TypeConverter
    fun fromTypePriority(type: PriorityHabit): Int = type.priorityCode

    @TypeConverter
    fun toTypePriority(code: Int): PriorityHabit = PriorityHabit.getPriorityAtCode(code)
}