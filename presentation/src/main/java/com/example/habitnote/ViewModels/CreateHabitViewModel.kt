package com.example.habitnote.ViewModels

import android.graphics.Bitmap
import android.widget.Button
import androidx.lifecycle.ViewModel

class CreateHabitViewModel: ViewModel() {

    fun setColorButton(button: Button, bitmap: Bitmap) {
        val bx = button.left + button.width / 2
        val by = button.top + button.height / 2

        if (bx <= bitmap.width) {
            val pix = bitmap.getPixel(bx, by)
            button.setBackgroundColor(pix)
        }
    }

    fun isCorrectDataFields(strings: List<String>): Boolean {
        var result = true
        strings.forEach {
            if (it == "") result = false
        }
        return result
    }
}