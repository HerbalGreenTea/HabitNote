package com.example.habitnote.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.habitnote.MainActivity
import com.example.habitnote.scenario.ScenarioCreateGoodHabit
import com.example.habitnote.scenario.ScenarioDeleteHabit
import com.example.habitnote.scenario.ScenarioIsCorrectHabit
import com.example.habitnote.scenario.ScenarioOpenScreenCreateHabit
import com.example.habitnote.screens.FragmentCreateHabitScreen
import com.example.habitnote.screens.FragmentHabitListScreen
import com.example.habitnote.screens.FragmentViewPagerScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HabitFlowTests: TestCase() {
    companion object {
        val title = ScenarioCreateGoodHabit.createGoodHabit.title
        val count = "0 / ${ScenarioCreateGoodHabit.createGoodHabit.count}"
        val frequency = "период: ${ScenarioCreateGoodHabit.createGoodHabit.frequency} дней"
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createHabit() = before {
    }.after {
    }.run {
        scenario(ScenarioCreateGoodHabit())
        scenario(ScenarioIsCorrectHabit(title, count, frequency))
        scenario(ScenarioDeleteHabit())
    }

    @Test
    fun editGoodHabit() = before {
    }.after {
    }.run {

        scenario(ScenarioCreateGoodHabit())
        scenario(ScenarioOpenScreenCreateHabit())

        step("редактирование данных") {
            FragmentCreateHabitScreen {
                tvTitle.replaceText("habit2")
                tvCount.replaceText("1")
                tvFrequency.replaceText("4")
            }
        }

        step("применить редактирование привычки") {
            FragmentCreateHabitScreen {
                btnCreateHabit.isClickable()
                btnCreateHabit.click()
            }
        }

        scenario(ScenarioIsCorrectHabit("habit2", "0 / 1", "период: 4 дней"))
        scenario(ScenarioDeleteHabit())
    }

    @Test
    fun editTypeGoodHabit() = before {
    }.after {
    }.run {

        scenario(ScenarioCreateGoodHabit())
        scenario(ScenarioOpenScreenCreateHabit())

        step("изменить тип привычки") {
            FragmentCreateHabitScreen {
                btnSelectTypeHabitBad.isClickable()
                btnSelectTypeHabitBad.click()
                btnCreateHabit.isClickable()
                btnCreateHabit.click()
            }
        }

        step("проверить привычку в списке плохих") {
            FragmentViewPagerScreen {
                viewPager.swipeLeft()
                FragmentHabitListScreen {
                    listHabits {
                        firstChild<FragmentHabitListScreen.Item> {
                            isVisible()
                            title.hasText("habit1")
                            flakySafely(timeoutMs = 7_000) {
                                bodyHabit.click()
                            }
                        }
                    }
                }
            }
        }

        step("изменить тип привычки") {
            FragmentCreateHabitScreen {
                btnSelectTypeHabitGood.isClickable()
                btnSelectTypeHabitGood.click()
                btnCreateHabit.isClickable()
                btnCreateHabit.click()
            }

            FragmentViewPagerScreen {
                viewPager.swipeRight()
            }
        }

        scenario(ScenarioDeleteHabit())
    }

    @Test
    fun doneHabit() = before {
    }.after {
    }.run {

        scenario(ScenarioCreateGoodHabit())

        step("нажать на кнопку выполнения привычки") {
            FragmentViewPagerScreen {
                FragmentHabitListScreen {
                    listHabits {
                        firstChild<FragmentHabitListScreen.Item> {
                            isVisible()
                            count.hasText("0 / 5")
                            btnDoneHabit.isClickable()
                            btnDoneHabit.click()
                            count.hasText("1 / 5")
                        }
                    }
                }
            }
        }

        scenario(ScenarioDeleteHabit())
    }
}