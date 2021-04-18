package com.example.habitnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.data.*
import com.example.habitnote.Adapters.ListHabitAdapter
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
    lateinit var habitInteractor: HabitInteractor

    private val habitsViewModel: ListHabitViewModel by lazy {
        @Suppress("UNCHECKED_CAST")
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ListHabitViewModel(habitInteractor) as T
            }
        }).get(ListHabitViewModel::class.java)
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

        App.appComponent.inject(this)

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
                val habits = habitsViewModel.readAllData.value
                if (habits != null)
                    updateList(habitsViewModel.applyFilter(habits, type, it))
            }

            habitsViewModel.readAllData.observe(viewLifecycleOwner) {
                updateList(habitsViewModel.applyFilter(it, type, TypeFilter.NONE))
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
}