package com.example.habitnote

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.data.Habit
import com.example.data.PriorityHabit
import com.example.data.TypeHabit
import com.example.habitnote.ViewModels.Event
import com.example.habitnote.ViewModels.SharedViewModel
import kotlinx.android.synthetic.main.fragment_create_habit.*
import kotlinx.android.synthetic.main.fragment_create_habit.view.*

class CreateHabitFragment : Fragment() {

    private val  sharedViewModel: SharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_habit, container, false)

        createColorPicker(view)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val habit = arguments?.getSerializable(HabitListFragment.HABIT) as? Habit
        val editHabit = arguments?.getBoolean(HabitListFragment.EDIT_HABIT, false) ?: false

        if (habit != null) {
            setDataHabitInField(view, habit)
        }

        view.btn_create_habit.setOnClickListener {
            if (isCorrectData(view)) {
                if (editHabit) {
                    val newHabit = createHabit(view)
                    newHabit.index = habit?.index

                    if (newHabit.type != habit?.type && habit != null) {
                        sharedViewModel.removeHabit.value = Event(habit)
                        sharedViewModel.createNewHabit.value = Event(newHabit)
                    } else {
                        sharedViewModel.editHabit.value = Event(newHabit)
                    }
                } else {
                    sharedViewModel.createNewHabit.value = Event(createHabit(view))
                }
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, R.string.toast_empty_field_create_habit, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isCorrectData(view: View): Boolean {
        return !(view.create_habit_title.text.toString() == ""
                || view.create_habit_frequency.text.toString() == ""
                || view.create_habit_count.text.toString() == "")
    }

    private fun createHabit(view: View): Habit {
        val checkTypeHabit = view.findViewById<RadioButton>(view.create_type_habit.checkedRadioButtonId)
        val priorityPosition = view.create_priority_habit.selectedItemPosition

        return Habit(
                null,
                view.create_habit_title.text.toString(),
                view.create_description_habit.text.toString(),
                PriorityHabit.getPriorityAtCode(priorityPosition),
                TypeHabit.getTypeAtCode(create_type_habit.indexOfChild(checkTypeHabit)),
                view.create_habit_frequency.text.toString().toInt(),
                view.create_habit_count.text.toString().toInt(),
                (view.create_habit_color.background as ColorDrawable).color
        )
    }

    private fun createColorPicker(view: View) {

        for (i in 0 until view.rg_btn_color_picker.size) {
            val button = (view.rg_btn_color_picker.getChildAt(i) as Button)

            button.viewTreeObserver.addOnGlobalLayoutListener {
                val bx = button.left + button.width / 2
                val by = button.top + button.height / 2

                val gradientBitmap = view.rg_btn_color_picker.background.toBitmap()
                if (bx <= gradientBitmap.width) {
                    val pix = gradientBitmap.getPixel(bx, by)
                    button.setBackgroundColor(pix)
                }

                button.setOnClickListener {
                    val color = (button.background as ColorDrawable).color
                    view.create_habit_color.setBackgroundColor(color)
                }
            }
        }
    }

    private fun setDataHabitInField(view: View, habit: Habit) {
        view.create_habit_title.setText(habit.title)
        view.create_description_habit.setText(habit.description)
        view.create_priority_habit.setSelection(habit.priority.priorityCode)

        when(habit.type.typeCode) {
            0 -> view.create_type_habit.btn_type_good.isChecked = true
            else -> view.create_type_habit.btn_type_bad.isChecked = true
        }

        view.create_habit_frequency.setText(habit.frequency.toString())
        view.create_habit_count.setText(habit.count.toString())
        view.create_habit_color.setBackgroundColor(habit.color)
    }
}