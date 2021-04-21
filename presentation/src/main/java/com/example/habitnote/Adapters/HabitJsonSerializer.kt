package com.example.habitnote.Adapters

import com.example.data.Habit
import com.example.data.HabitUid
import com.example.data.PriorityHabit
import com.example.data.TypeHabit
import com.google.gson.*
import java.lang.reflect.Type

class HabitJsonSerializer: JsonSerializer<Habit?> {
    override fun serialize(
        src: Habit?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement = JsonObject().apply {
        if (src != null) {
            addProperty("title", src.title)
            addProperty("description", src.description)
            addProperty("priority", src.priority.priorityCode)
            addProperty("type", src.type.typeCode)
            addProperty("frequency", src.frequency)
            addProperty("count", src.count)
            addProperty("color", src.color)
            addProperty("date", src.date)
        }
    }
}

class HabitJsonDeserializer: JsonDeserializer<Habit> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Habit {
        if (json != null) {
            val habit = Habit(
                    null,
                    json.asJsonObject?.get("title")?.asString ?: "",
                    json.asJsonObject?.get("description")?.asString ?: "",
                    PriorityHabit.getPriorityAtCode(json.asJsonObject?.get("priority")?.asInt ?: 0),
                    TypeHabit.getTypeAtCode(json.asJsonObject?.get("type")?.asInt ?: 0),
                    json.asJsonObject?.get("frequency")?.asInt ?: 0,
                    json.asJsonObject?.get("count")?.asInt ?: 0,
                    json.asJsonObject?.get("color")?.asInt ?: 0,
                    json.asJsonObject?.get("date")?.asInt ?: 0
            )
            habit.uid = HabitUid(json.asJsonObject?.get("uid")?.asString)
            return habit
        } else {
            return Habit(null)
        }
    }
}