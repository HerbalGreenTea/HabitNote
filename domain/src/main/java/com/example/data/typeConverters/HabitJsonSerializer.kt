package com.example.data.typeConverters

import com.example.data.entities.Habit
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
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