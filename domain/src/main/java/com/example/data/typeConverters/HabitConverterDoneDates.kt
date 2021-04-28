package com.example.data.typeConverters

import androidx.room.TypeConverter

class HabitConverterDoneDates {

    @TypeConverter
    fun fromDoneDate(doneDates: List<Long>): String {
        var res = ""
        if (doneDates.isNotEmpty()) {
            res = doneDates.map { it.toString() }.joinToString(",") { it }
        }
        return res
    }

    @TypeConverter
    fun toDoneDate(doneDate: String): List<Long> {
        return if (doneDate.isNotEmpty())
            doneDate.replace(" ", "")
                    .split(",")
                    .map { it.toLong() }
        else listOf()
    }
}