package com.example.data.useCases

import com.example.data.entities.Habit

interface OnItemClickListener {
    fun clickItem(habit: Habit)
}