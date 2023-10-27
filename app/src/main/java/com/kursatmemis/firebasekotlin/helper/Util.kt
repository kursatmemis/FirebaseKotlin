package com.kursatmemis.firebasekotlin.helper

import android.widget.EditText

fun areEditTextsEmpty(vararg editTexts: EditText) : Boolean {
    for (editText in editTexts) {
        val text = editText.text.toString()
        if (text.isEmpty()) {
            return true
        }
    }

    return false
}