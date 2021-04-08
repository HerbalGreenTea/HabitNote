package com.example.habitnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.data.*
import com.example.habitnote.ViewModels.SharedViewModel
import kotlinx.android.synthetic.main.fragment_filter_habits.view.*

class FilterHabitsFragment : Fragment() {

    private val  sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter_habits, container, false)
    }

    private fun applyFilter(typeFilter: TypeFilter) {
        sharedViewModel.actionFilter.value = Event(typeFilter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btn_filter_priority_low.setOnClickListener {
            applyFilter(TypeFilter.PRIORITY_LOW)
        }

        view.btn_filter_priority_mid.setOnClickListener {
            applyFilter(TypeFilter.PRIORITY_MID)
        }

        view.btn_filter_priority_high.setOnClickListener {
            applyFilter(TypeFilter.PRIORITY_HIGH)
        }

        view.btn_sort_increase_count.setOnClickListener {
            applyFilter(TypeFilter.SORT_INCREASE_COUNT)
        }

        view.btn_sort_decrease_count.setOnClickListener {
            applyFilter(TypeFilter.SORT_DECREASE_COUNT)
        }

        view.btn_sort_increase_frequency.setOnClickListener {
            applyFilter(TypeFilter.SORT_INCREASE_FREQUENCY)
        }

        view.btn_sort_decrease_frequency.setOnClickListener {
            applyFilter(TypeFilter.SORT_DECREASE_FREQUENCY)
        }

        view.btn_cancel.setOnClickListener {
            applyFilter(TypeFilter.NONE)
        }
    }
}