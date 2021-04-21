package com.example.data.typeConverters

import androidx.room.TypeConverter
import com.example.data.entities.TypeHabit

class HabitConverterType {

    @TypeConverter
    fun fromTypeHabit(type: TypeHabit): Int = type.typeCode

    @TypeConverter
    fun toTypeHabit(code: Int): TypeHabit = TypeHabit.getTypeAtCode(code)
}