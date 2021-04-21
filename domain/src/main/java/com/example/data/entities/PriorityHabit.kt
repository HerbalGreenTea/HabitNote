package com.example.data.entities

enum class PriorityHabit (val priorityCode: Int) {
    LOW(0),
    MID(1),
    HIGH(2);

    companion object {
        fun getPriorityAtCode(code: Int): PriorityHabit {
            return when(code) {
                0 -> LOW
                1 -> MID
                else -> HIGH
            }
        }
        // поискать из коробки или сделать автоматическое решение, енамов может быть много
    }
}