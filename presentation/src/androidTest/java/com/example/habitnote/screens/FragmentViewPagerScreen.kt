package com.example.habitnote.screens

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.pager.KViewPager
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.tabs.KTabLayout
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.example.habitnote.R
import com.kaspersky.kaspresso.screens.KScreen
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf.allOf

object FragmentViewPagerScreen: KScreen<FragmentViewPagerScreen>() {
    val btnOpenScreenCreateHabit = KButton { withId(R.id.btn_open_create_habit) }
    val viewPager = KViewPager { withId(R.id.view_pager) }

    override val layoutId: Int
        get() = R.layout.fragment_view_pager
    override val viewClass: Class<*>
        get() = FragmentViewPagerScreen::class.java
}






















//    val listHabits = KRecyclerView(
//        builder = { onView(Matchers.allOf(
//            ViewMatchers.withId(R.id.rv_habits), ViewMatchers.withEffectiveVisibility(
//                ViewMatchers.Visibility.VISIBLE)
//        )) },
//        itemTypeBuilder = { itemType(FragmentViewPagerScreen::Item) }
//    )

//    val listHaits = onView(allOf(
//        withEffectiveVisibility(Visibility.VISIBLE),
//        withId(R.id.rv_habits)))
//        .check(matches(isDisplayed()))