package com.example.habitnote.screens

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.habitnote.R
import org.hamcrest.Matcher

class FragmentHabitListScreen: Screen<FragmentHabitListScreen>() {
    val listHabits = KRecyclerView(
            builder = { withId(R.id.rv_habits) },
            itemTypeBuilder = { itemType(FragmentHabitListScreen::Item) }
    )

    internal class Item(parent: Matcher<View>): KRecyclerItem<Item>(parent) {
        val title = KTextView(parent) { R.id.habit_title }
        val count = KTextView(parent) { R.id.habit_count }
        val frequency = KTextView(parent) { R.id.habit_frequency }
        val typeHabit = KTextView(parent) { R.id.habit_type }
        val priorityHabit = KTextView(parent) { R.id.habit_priority }
    }

    // todo добавить UI тесты для списка привычек
}