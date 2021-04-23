package com.example.data.typeConverters

import com.example.data.entities.Habit
import com.google.gson.*
import java.lang.reflect.Type

class HabitJsonSerializer: JsonSerializer<Habit?> {
    override fun serialize(
            src: Habit?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
    ): JsonElement {
        val obj = JsonObject()

        if (src != null) {
            obj.apply {
                addProperty("uid", src.id.uid)
                addProperty("title", src.title)
                addProperty("description", src.description)
                addProperty("priority", src.priority.priorityCode)
                addProperty("type", src.type.typeCode)
                addProperty("frequency", src.frequency)
                addProperty("count", src.count)
                addProperty("color", src.color)
                addProperty("date", src.date)
            }

            if (src.doneDates.isNotEmpty()) {
                val doneDateArr = JsonArray()
                src.doneDates.forEach { doneDateArr.add(it) }
                obj.add("done_dates", doneDateArr)
            }
        }

        return obj
    }
}