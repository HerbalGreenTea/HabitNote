package com.example.data

enum class TypeHabit(val typeCode: Int) {
    GOOD(0),
    BAD(1);

    companion object {
        fun getTypeAtCode(code: Int): TypeHabit {
            return when (code) {
                0 -> GOOD
                else -> BAD
            }
        }
    }
}