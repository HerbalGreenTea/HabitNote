package com.example.habitnote

import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_habit_list.*
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

    private lateinit var sharedViewModel: SharedViewModel

    private val habitsViewModel: ListHabitViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ListHabitViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_habit_list, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = TypeHabit.getTypeAtCode(arguments?.getInt(TYPE_HABIT) ?: 0)

        view.rv_habits.adapter = ListHabitAdapter().apply {

            setData(if (type == TypeHabit.GOOD) habitsViewModel.getGoodHabits()
                    else habitsViewModel.getBadHabits())

            sharedViewModel.removeHabit.observe(viewLifecycleOwner) {
                habitsViewModel.removeHabit(it)
                notifyItemChanged(it.index as Int)
            }

            sharedViewModel.createNewHabit.observe(viewLifecycleOwner) {
                habitsViewModel.addHabit(it)
                addHabit(it)
            }

            sharedViewModel.editHabit.observe(viewLifecycleOwner) {
                val habit = habitsViewModel.addHabit(it)
                if (type == it.type) updateHabit(habit)
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