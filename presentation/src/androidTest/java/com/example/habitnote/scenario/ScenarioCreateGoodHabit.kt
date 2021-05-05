package com.example.habitnote.scenario

import com.example.data.entities.Habit
import com.example.data.entities.PriorityHabit
import com.example.data.entities.TypeHabit
import com.example.habitnote.screens.FragmentCreateHabitScreen
import com.example.habitnote.screens.FragmentViewPagerScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class ScenarioCreateGoodHabit: Scenario() {
    companion object {
        val createGoodHabit = Habit(
              "habit1",
              "des1",
              PriorityHabit.LOW,
              TypeHabit.GOOD,
              3,
              5
        )
    }

    override val steps: TestContext<Unit>.() -> Unit = {
        step("открыть экран создания привычки") {
            FragmentViewPagerScreen {
                btnOpenScreenCreateHabit.isVisible()
                btnOpenScreenCreateHabit.isClickable()
                btnOpenScreenCreateHabit.click()
            }
        }

        step("заполнить поля") {
            FragmentCreateHabitScreen {
                tvTitle.replaceText(createGoodHabit.title)
                tvCount.replaceText(createGoodHabit.count.toString())
                tvFrequency.replaceText(createGoodHabit.frequency.toString())
                tvDescription.replaceText(createGoodHabit.description)
            }
        }

        step("создать привычку") {
            FragmentCreateHabitScreen {
                btnCreateHabit.isClickable()
                btnCreateHabit.click()
            }
        }
    }
}