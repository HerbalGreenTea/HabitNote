package com.example.habitnote.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.habitnote.Adapters.PagerAdapter
import com.example.habitnote.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager.*
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.view_pager.adapter = PagerAdapter(requireActivity())

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            if (position == 0) tab.setText(R.string.habit_type_good)
            else tab.setText(R.string.habit_type_bad)
        }.attach()

        view.btn_open_create_habit.setOnClickListener {
            findNavController().navigate(R.id.createHabitFragment)
        }
    }
}