package com.example.habitnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.*
import com.example.habitnote.ViewModels.ListHabitViewModel
import kotlinx.android.synthetic.main.fragment_filter_habits.view.*
import javax.inject.Inject

class FilterHabitsFragment : Fragment() {

    @Inject
    lateinit var habitsViewModel: ListHabitViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filter_habits, container, false)

        MainActivity.viewModelComponent.inject(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btn_filter_priority_low.setOnClickListener {
            habitsViewModel.setValueActionFilter(TypeFilter.PRIORITY_LOW)
        }

        view.btn_filter_priority_mid.setOnClickListener {
            habitsViewModel.setValueActionFilter(TypeFilter.PRIORITY_MID)
        }

        view.btn_filter_priority_high.setOnClickListener {
            habitsViewModel.setValueActionFilter(TypeFilter.PRIORITY_HIGH)
        }

        view.btn_sort_increase_count.setOnClickListener {
            habitsViewModel.setValueActionFilter(TypeFilter.SORT_INCREASE_COUNT)
        }

        view.btn_sort_decrease_count.setOnClickListener {
            habitsViewModel.setValueActionFilter(TypeFilter.SORT_DECREASE_COUNT)
        }

        view.btn_sort_increase_frequency.setOnClickListener {
            habitsViewModel.setValueActionFilter(TypeFilter.SORT_INCREASE_FREQUENCY)
        }

        view.btn_sort_decrease_frequency.setOnClickListener {
            habitsViewModel.setValueActionFilter(TypeFilter.SORT_DECREASE_FREQUENCY)
        }

        view.btn_cancel.setOnClickListener {
            habitsViewModel.setValueActionFilter(TypeFilter.NONE)
        }
    }
}