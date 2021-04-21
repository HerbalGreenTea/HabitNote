package com.example.data.typeConverters

import androidx.room.TypeConverter
import com.example.data.entities.HabitUid

class HabitConverterUid {
    @TypeConverter
    fun fromHabitUid(habitUid: HabitUid): String? = habitUid.uid

    @TypeConverter
    fun toHabitUid(uid: String?): HabitUid = HabitUid(uid)
}