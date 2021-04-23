package com.example.data.typeConverters

import com.example.data.entities.Habit
import com.example.data.entities.HabitUid
import com.example.data.entities.PriorityHabit
import com.example.data.entities.TypeHabit
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class HabitJsonDeserializer: JsonDeserializer<Habit> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Habit {
        if (json != null) {
            val habit = Habit(
                json.asJsonObject?.get("title")?.asString ?: "",
                json.asJsonObject?.get("description")?.asString ?: "",
                PriorityHabit.getPriorityAtCode(json.asJsonObject?.get("priority")?.asInt ?: 0),
                TypeHabit.getTypeAtCode(json.asJsonObject?.get("type")?.asInt ?: 0),
                json.asJsonObject?.get("frequency")?.asInt ?: 0,
                json.asJsonObject?.get("count")?.asInt ?: 0,
                json.asJsonObject?.get("color")?.asInt ?: 0,
                json.asJsonObject?.get("date")?.asLong ?: 0
            )

            // todo больше узнать о Gson и лучше его приготовить

            habit.id = HabitUid(json.asJsonObject?.get("uid")?.asString)
            val doneDate = json.asJsonObject?.get("done_dates")?.asJsonArray

            if (doneDate != null)
                habit.doneDates = doneDate.map { date -> date.asLong } as MutableList<Long>

            return habit
        } else {
            return Habit()
        }
    }
}