package com.example.habitnote.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.data.TypeHabit
import com.example.habitnote.HabitListFragment

class PagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount() = TypeHabit.values().size

    override fun createFragment(position: Int): Fragment =
            HabitListFragment.newInstance(TypeHabit.getTypeAtCode(position))

}