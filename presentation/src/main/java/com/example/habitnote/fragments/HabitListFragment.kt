package com.example.habitnote.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entities.Habit
import com.example.data.entities.TypeFilter
import com.example.data.entities.TypeHabit
import com.example.data.useCases.OnItemClickListener
import com.example.habitnote.Adapters.ListHabitAdapter
import com.example.habitnote.MainActivity
import com.example.habitnote.R
import com.example.habitnote.ViewModels.ListHabitViewModel
import com.example.habitnote.ViewModels.SharedViewModel
import kotlinx.android.synthetic.main.fragment_habit_list.*
import kotlinx.android.synthetic.main.fragment_habit_list.view.*
import javax.inject.Inject

class HabitListFragment : Fragment() {
    companion object {
        const val EDIT_HABIT = "edit_habit"
        const val HABIT = "habit"
        private const val TYPE_HABIT = "type_habit"

        fun newInstance(typeHabit: TypeHabit): HabitListFragment {
            val fragment = HabitListFragment()
            fragment.arguments = Bundle()
            fragment.arguments?.putInt(TYPE_HABIT, typeHabit.typeCode)
            return fragment
        }
    }

    @Inject
    lateinit var habitsViewModel: ListHabitViewModel
    @Inject
    lateinit var sharedViewModel: SharedViewModel

    private var viewFragment: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        if (viewFragment == null)
            viewFragment = inflater.inflate(R.layout.fragment_habit_list, container, false)

        MainActivity.viewModelComponent.inject(this)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = TypeHabit.getTypeAtCode(arguments?.getInt(TYPE_HABIT) ?: 0)

        view.rv_habits.adapter = ListHabitAdapter().apply {

            sharedViewModel.createNewHabit.observe(viewLifecycleOwner) {
                habitsViewModel.addHabit(it)
            }

            sharedViewModel.editHabit.observe(viewLifecycleOwner) {
                habitsViewModel.updateHabit(it)
            }

            habitsViewModel.actionFilter.observe(viewLifecycleOwner) {
                val habits = habitsViewModel.readAllDataHabits.value
                if (habits != null)
                    updateList(habitsViewModel.applyFilter(habits, type, it))
            }

            habitsViewModel.readAllDataHabits.observe(viewLifecycleOwner) {
                updateList(habitsViewModel.applyFilter(it, type, TypeFilter.NONE))
            }

            setOnItemClickListenerHabitEdit(object : OnItemClickListener {
                override fun clickItem(habit: Habit) {
                    val bundle = Bundle().apply {
                        putSerializable(HABIT, habit)
                        putBoolean(EDIT_HABIT, true)
                    }
                    findNavController().navigate(R.id.createHabitFragment, bundle)
                }
            })

            setOnItemClickListenerHabitDone(object : OnItemClickListener {
                override fun clickItem(habit: Habit) {
                    val showMessage1 = { showMessage(habit,
                            "Стоит выполнить еще ${habit.count - habit.doneDates.size} раз",
                            "You are breathing",
                            "Можете выполнить еще ${habit.count - habit.doneDates.size} раз",
                            "Хватит это делать"

                    ) }

                    val showMessage2 = {
                        showMessage(habit,
                                "Почти получилось дойти до цели, попробуйте снова",
                                "так держать, вы дошли цель",
                                "Отлично, вы выполнили меньше, чем можно",
                                "Лимит достигнут"
                        )
                    }

                    habitsViewModel.doneHabit(habit, { showMessage1() }, { showMessage2() } )
                }
            })

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val removeHabit = (viewHolder as ListHabitAdapter.HabitsViewHolder).getDataHabit()
                    habitsViewModel.deleteHabit(removeHabit)
                }
            }).attachToRecyclerView(rv_habits)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showMessage(habit: Habit, str1: String, str2: String, str3: String, str4: String) {
        when(habit.type) {
            TypeHabit.GOOD -> {
                if (habit.count > habit.doneDates.size) showToast(str1)
                else showToast(str2)
            }

            TypeHabit.BAD -> {
                if (habit.count > habit.doneDates.size) showToast(str3)
                else showToast(str4)
            }
        }
    }
}