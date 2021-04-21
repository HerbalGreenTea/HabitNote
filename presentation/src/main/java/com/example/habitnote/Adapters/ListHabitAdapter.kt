package com.example.habitnote.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entities.Habit
import com.example.data.entities.PriorityHabit
import com.example.data.entities.TypeHabit
import com.example.data.useCases.OnItemClickListener
import com.example.habitnote.R
import kotlinx.android.synthetic.main.item_habit.view.*

class ListHabitAdapter(
        private var habits: List<Habit> = listOf()
): RecyclerView.Adapter<ListHabitAdapter.HabitsViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    class HabitsViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_habit, parent, false)) {

        private lateinit var dataHabit: Habit

        fun getDataHabit(): Habit = dataHabit

        fun bind(habit: Habit) {
            dataHabit = habit

            itemView.apply {
                habit_title.text = habit.title
                habit_title.setTextColor(habit.color)

                habit_type.setText(
                        if (habit.type == TypeHabit.GOOD) R.string.habit_type_good
                        else R.string.habit_type_bad
                )

                habit_priority.setText(
                        when (habit.priority) {
                            PriorityHabit.LOW -> R.string.habit_priority_low
                            PriorityHabit.MID -> R.string.habit_priority_mid
                            else -> R.string.habit_priority_high
                        }
                )

                habit_count.text = habit.count.toString()
                habit_frequency.text = habit.frequency.toString()
                habit_color.setBackgroundColor(habit.color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HabitsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: HabitsViewHolder, position: Int) {
        val habit = habits[position]
        holder.bind(habit)

        if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
            holder.itemView.setOnClickListener {
                (onItemClickListener as OnItemClickListener).clickItem(habit)
            }
        }
    }

    override fun getItemCount(): Int = habits.size

    fun updateList(newHabits: List<Habit>) {
        val diffUtil: DiffUtil.DiffResult = DiffUtil.calculateDiff(DiffUtilHabits(this.habits, newHabits))
        this.habits = newHabits
        diffUtil.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}