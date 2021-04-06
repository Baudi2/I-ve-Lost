package ru.startandroid.develop.testprojectnavigation.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Exception

//? Чтобы скрыть клавиатуру после нажатия кнопки
fun hideKeyboard(view: View, activity: Activity) {
    try {
        val imn =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}