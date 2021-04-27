package com.example.habitnote

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.example.habitnote.screens.FragmentCreateHabitScreen
import com.example.habitnote.screens.FragmentViewPagerScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateHabitTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun openCreateHabitTest() {
        Screen.onScreen<FragmentViewPagerScreen> {
            btnOpenScreenCreateHabit.isClickable()
            btnOpenScreenCreateHabit.click()
        }
    }

    @Test
    fun createHabit() {
        Screen.onScreen<FragmentViewPagerScreen> {
            btnOpenScreenCreateHabit.isClickable()
            btnOpenScreenCreateHabit.click()
        }

        Screen.onScreen<FragmentCreateHabitScreen> {
            tvTitle.replaceText("habit1")
            tvCount.replaceText("5")
            tvFrequency.replaceText("3")
            tvDescription.replaceText("test1")
            btnCreateHabit.isClickable()
            btnCreateHabit.click()
        }
    }
}