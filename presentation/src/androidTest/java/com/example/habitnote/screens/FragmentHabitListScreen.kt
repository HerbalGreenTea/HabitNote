package com.example.habitnote.screens

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.agoda.kakao.common.views.KSwipeView
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.habitnote.R
import com.kaspersky.kaspresso.screens.KScreen
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

object FragmentHabitListScreen: KScreen<FragmentHabitListScreen>() {

    val listHabits = KRecyclerView(
            builder = {
                withId(R.id.rv_habits)
                isCompletelyDisplayed()
                isDisplayed()
            },
            itemTypeBuilder = { itemType(FragmentHabitListScreen::Item) }
    )

    internal class Item(parent: Matcher<View>): KRecyclerItem<Item>(parent) {
        val title = KTextView(parent) { withId(R.id.habit_title) }
        val count = KTextView(parent) { withId(R.id.habit_count) }
        val frequency = KTextView(parent) { withId(R.id.habit_frequency) }
        val priorityHabit = KTextView(parent) { withId(R.id.habit_priority) }
        val bodyHabit = KView(parent) { withId(R.id.body_habit) }
        val color = KSwipeView(parent) { withId(R.id.habit_color) }
        val btnDoneHabit = KButton(parent) { withId(R.id.btn_done_habit) }
    }

    override val layoutId: Int
        get() = R.layout.fragment_habit_list
    override val viewClass: Class<*>
        get() = FragmentHabitListScreen::class.java
}