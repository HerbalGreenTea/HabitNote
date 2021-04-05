package com.example.habitnote

import androidx.recyclerview.widget.DiffUtil
import com.example.data.Habit

class DiffUtilHabits(
        private var oldHabits: List<Habit>,
        private var newHabits: List<Habit>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldHabits.size

    override fun getNewListSize(): Int = newHabits.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldHabits[oldItemPosition].id == newHabits[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldHabits[oldItemPosition] == newHabits[newItemPosition]
    }
}