package com.example.habitnote.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entities.Habit
import com.example.data.entities.PriorityHabit
import com.example.data.useCases.OnItemClickListener
import com.example.habitnote.R
import kotlinx.android.synthetic.main.item_habit.view.*

class ListHabitAdapter(
        private var habits: List<Habit> = listOf(),
        private var onItemClickListenerHabitEdit: OnItemClickListener? = null,
        private var onItemClickListenerHabitDone: OnItemClickListener? = null
): RecyclerView.Adapter<ListHabitAdapter.HabitsViewHolder>() {

    class HabitsViewHolder(inflater: LayoutInflater, parent: ViewGroup)
        : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_habit, parent, false)) {

        private lateinit var dataHabit: Habit

        fun getDataHabit(): Habit = dataHabit

        fun bind(habit: Habit) {
            // данные привычки не привзяанные ссылкой к habits для более корректной работы DiffUtil
            dataHabit = Habit(
                    habit.title,
                    habit.description,
                    habit.priority,
                    habit.type,
                    habit.frequency,
                    habit.count,
                    habit.color,
                    habit.date,
                    habit.doneDates
            )
            dataHabit.id = habit.id

            itemView.apply {
                body_habit.habit_title.text = habit.title
                body_habit.habit_title.setTextColor(habit.color)

                body_habit.habit_priority.setText(
                        when (habit.priority) {
                            PriorityHabit.LOW -> R.string.habit_priority_low
                            PriorityHabit.MID -> R.string.habit_priority_mid
                            else -> R.string.habit_priority_high
                        }
                )

                body_habit.habit_frequency.text = "период: ${habit.frequency} дней"
                info_count.habit_count.text = "${habit.doneDates.size} / ${habit.count}"
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

        if (onItemClickListenerHabitEdit != null && position != RecyclerView.NO_POSITION) {
            holder.itemView.body_habit.setOnClickListener {
                (onItemClickListenerHabitEdit as OnItemClickListener).clickItem(holder.getDataHabit())
            }
        }

        if (onItemClickListenerHabitDone != null) {
            holder.itemView.info_count.btn_done_habit.setOnClickListener {
                (onItemClickListenerHabitDone as OnItemClickListener).clickItem(holder.getDataHabit())
            }
        }
    }

    override fun getItemCount(): Int = habits.size

    fun updateList(newHabits: List<Habit>) {
        val diffUtil: DiffUtil.DiffResult = DiffUtil.calculateDiff(DiffUtilHabits(this.habits, newHabits))
        this.habits = newHabits
        diffUtil.dispatchUpdatesTo(this)
    }

    fun setOnItemClickListenerHabitEdit(onItemClickListener: OnItemClickListener) {
        this.onItemClickListenerHabitEdit = onItemClickListener
    }

    fun setOnItemClickListenerHabitDone(onItemClickListener: OnItemClickListener) {
        this.onItemClickListenerHabitDone = onItemClickListener
    }
}