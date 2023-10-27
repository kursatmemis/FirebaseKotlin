package com.kursatmemis.firebasekotlin.helper

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun showProgressBar(progressBar: ProgressBar) {
    progressBar.visibility = View.VISIBLE
}

fun closeProgressBar(progressBar: ProgressBar) {
    progressBar.visibility = View.INVISIBLE
}