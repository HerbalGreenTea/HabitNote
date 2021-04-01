package com.example.habitnote.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.*
import com.example.habitnote.R
import kotlinx.android.synthetic.main.item_habit.view.*

class ListHabitAdapter(
        private var habits: MutableList<Habit>
): RecyclerView.Adapter<ListHabitAdapter.HabitsViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    class HabitsViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_habit, parent, false)) {

        fun bind(habit: Habit) {
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
        habits[position].index = position
        val habit = habits[position]
        holder.bind(habit)

        if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
            holder.itemView.setOnClickListener {
                habits[holder.adapterPosition].index = holder.adapterPosition
                (onItemClickListener as OnItemClickListener).clickItem(habits[holder.adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = habits.size

    fun lastNotifyItemChanged() {
        notifyItemChanged(this.habits.size - 1)
    }

    fun updateHabit(habit: Habit) {
        if (habit.index != null) {
            habits[habit.index as Int].run {
                title = habit.title
                description = habit.description
                type = habit.type
                priority = habit.priority
                frequency = habit.frequency
                count = habit.count
                color = habit.color
            }
            notifyItemChanged(habit.index as Int)
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}