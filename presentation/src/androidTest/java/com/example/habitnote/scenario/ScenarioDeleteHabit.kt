package com.example.habitnote.scenario

import com.example.habitnote.screens.FragmentHabitListScreen
import com.example.habitnote.screens.FragmentViewPagerScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class ScenarioDeleteHabit: Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        step("удалить привычку") {
            FragmentViewPagerScreen {
                FragmentHabitListScreen {
                    listHabits {
                        firstChild<FragmentHabitListScreen.Item> {
                            color.swipeRight()
                        }
                    }
                }
            }
        }
    }
}