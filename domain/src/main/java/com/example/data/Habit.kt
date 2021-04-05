package com.example.data

import java.io.Serializable

data class Habit (
        var id: Int?,
        var title: String,
        var description: String,
        var priority: PriorityHabit,
        var type: TypeHabit,
        var frequency: Int,
        var count: Int,
        var color: Int): Serializable