package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.io.Serializable

@Entity(tableName = "habit_table")
@TypeConverters(
        HabitConverterPriority::class,
        HabitConverterType::class,
        HabitConverterUid::class)
data class Habit (
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        var title: String = "",
        var description: String = "",
        var priority: PriorityHabit = PriorityHabit.LOW,
        var type: TypeHabit = TypeHabit.GOOD,
        var frequency: Int = 0,
        var count: Int = 0,
        var color: Int = 0,
        var date: Int = 0): Serializable {
        var uid: HabitUid? = null
    }

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

class HabitConverterUid {
    @TypeConverter
    fun fromHabitUid(habitUid: HabitUid): String? {
        return habitUid.uid
    }

    @TypeConverter
    fun toHabitUid(uid: String?): HabitUid {
        return HabitUid(uid)
    }
}