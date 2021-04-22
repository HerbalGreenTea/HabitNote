package com.example.data.typeConverters

import androidx.room.TypeConverter

class HabitConverterDoneDates {

    @TypeConverter
    fun fromDoneDate(doneDates: List<Long>): String {
        return if (doneDates.isNotEmpty()) {
            doneDates.map { it.toString() }
                    .reduce { dates, date -> "${date}, $date" }
        } else ""
    }

    @TypeConverter
    fun toDoneDate(doneDate: String): List<Long> {
        return if (doneDate.isNotEmpty())
            doneDate.split(",").map { it.toLong() }
        else listOf()
    }
}