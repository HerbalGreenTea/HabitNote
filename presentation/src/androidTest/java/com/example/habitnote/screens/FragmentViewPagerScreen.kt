package com.example.habitnote.screens

import com.agoda.kakao.pager.KViewPager
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.habitnote.R

class FragmentViewPagerScreen: Screen<FragmentViewPagerScreen>() {
    val btnOpenScreenCreateHabit = KButton { withId(R.id.btn_open_create_habit) }
    val viewPager = KViewPager { withId(R.id.view_pager) }
}