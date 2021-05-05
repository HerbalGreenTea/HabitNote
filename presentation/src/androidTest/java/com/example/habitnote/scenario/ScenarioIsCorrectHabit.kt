package com.example.habitnote.scenario

import com.example.habitnote.screens.FragmentHabitListScreen
import com.example.habitnote.screens.FragmentViewPagerScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class ScenarioIsCorrectHabit(
        habitTitle: String,
        habitCount: String,
        habitFrequency: String
): Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        step("проверить данные привычки") {
            FragmentViewPagerScreen {
                FragmentHabitListScreen {
                    listHabits {
                        firstChild<FragmentHabitListScreen.Item> {
                            isVisible()
                            title.hasText(habitTitle)
                            count.hasText(habitCount)
                            frequency.hasText(habitFrequency)
                        }
                    }
                }
            }
        }
    }
}