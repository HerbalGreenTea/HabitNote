package com.example.habitnote.scenario

import com.example.habitnote.screens.FragmentCreateHabitScreen
import com.example.habitnote.screens.FragmentHabitListScreen
import com.example.habitnote.screens.FragmentViewPagerScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class ScenarioOpenScreenCreateHabit: Scenario() {

    override val steps: TestContext<Unit>.() -> Unit = {
        step("открыть экран редактирования привычки") {
            FragmentViewPagerScreen {
                FragmentHabitListScreen {
                    listHabits {
                        firstChild<FragmentHabitListScreen.Item> {
                            isVisible()
                            bodyHabit.isClickable()
                            bodyHabit.click()
                        }
                    }
                }
            }
        }

        step("проверить корректность данных") {
            FragmentCreateHabitScreen {
                tvTitle.hasText(ScenarioCreateGoodHabit.createGoodHabit.title)
                tvCount.hasText(ScenarioCreateGoodHabit.createGoodHabit.count.toString())
                tvFrequency.hasText(ScenarioCreateGoodHabit.createGoodHabit.frequency.toString())
                tvDescription.hasText(ScenarioCreateGoodHabit.createGoodHabit.description)
            }
        }
    }
}