package com.example.habitnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.data.Habit
import com.example.data.OnItemClickListener
import com.example.data.TypeHabit
import com.example.habitnote.Adapters.ListHabitAdapter
import com.example.habitnote.ViewModels.ListHabitViewModel
import com.example.habitnote.ViewModels.SharedViewModel
import kotlinx.android.synthetic.main.fragment_habit_list.view.*

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

    private val habitsViewModel: ListHabitViewModel by lazy {
        ViewModelProvider(requireActivity()).get(ListHabitViewModel::class.java)
    }

    private val  sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    private var viewFragment: View? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        if (viewFragment == null)
            viewFragment = inflater.inflate(R.layout.fragment_habit_list, container, false)

        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = TypeHabit.getTypeAtCode(arguments?.getInt(TYPE_HABIT) ?: 0)

        view.rv_habits.adapter = ListHabitAdapter().apply {

            sharedViewModel.removeHabit.observe(viewLifecycleOwner) {
                habitsViewModel.switchTypeHabit(type, it)
                updateList(habitsViewModel.getHabits(type))
            }

            sharedViewModel.createNewHabit.observe(viewLifecycleOwner) {
                habitsViewModel.createNewHabit(type, it)
                updateList(habitsViewModel.getHabits(type))
            }

            sharedViewModel.editHabit.observe(viewLifecycleOwner) {
                habitsViewModel.editHabit(type, it)
                updateList(habitsViewModel.getHabits(type))
            }

            setOnItemClickListener(object : OnItemClickListener {
                override fun clickItem(habit: Habit) {
                    val bundle = Bundle().apply {
                        putSerializable(HABIT, habit)
                        putBoolean(EDIT_HABIT, true)
                    }
                    findNavController().navigate(R.id.createHabitFragment, bundle)
                }
            })
        }
    }
}