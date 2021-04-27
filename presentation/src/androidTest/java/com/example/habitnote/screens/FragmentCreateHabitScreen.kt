package com.example.habitnote.screens

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.habitnote.R

class FragmentCreateHabitScreen: Screen<FragmentCreateHabitScreen>() {
    val tvTitle = KEditText { withId(R.id.create_habit_title) }
    val tvCount = KEditText { withId(R.id.create_habit_count) }
    val tvDescription = KEditText { withId(R.id.create_description_habit) }
    val tvFrequency = KEditText { withId(R.id.create_habit_frequency) }
    val btnCreateHabit = KButton { withId(R.id.btn_create_habit) }
}