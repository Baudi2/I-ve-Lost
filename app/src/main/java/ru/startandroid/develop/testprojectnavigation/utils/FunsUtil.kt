package ru.startandroid.develop.testprojectnavigation.utils

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.drawerlayout.widget.DrawerLayout
import ru.startandroid.develop.testprojectnavigation.R
import java.lang.Exception

//? Чтобы скрыть клавиатуру после нажатия кнопки
fun hideKeyboard(view: View) {
    try {
        val imn =
            APP_ACTIVITY.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun shortToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun longToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_LONG).show()
}

fun stringGet(id: Int) = APP_ACTIVITY.resources.getString(id)

//? копирует текст из textView и сохраняет в буфер, i.e. clipboard
fun copyText(textView: TextView) {
    val manager = APP_ACTIVITY.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", textView.text)
    manager.setPrimaryClip(clipData)
}

//? функция вызова popupMenu, используется в нашёл, потерял и фрагменте сообщений
@SuppressLint("RestrictedApi")
fun showPopup(
    view: View,
    menu: Int,
    message: String,
    secondMessage: String,
    menuItemId: Int,
    secondMenuItemId: Int,
    textView: TextView?
) {
    val menuBuilder = MenuBuilder(APP_ACTIVITY)
    val menuInflater = MenuInflater(APP_ACTIVITY)
    menuInflater.inflate(menu, menuBuilder)
    val optionsMenu = MenuPopupHelper(APP_ACTIVITY, menuBuilder, view)
    optionsMenu.setForceShowIcon(true)
    //? если textView не равен нулю значит мы в фрагменте сообщений, а там нужен аттрибут гравитации
    if (textView != null) optionsMenu.gravity = Gravity.END


    menuBuilder.setCallback((object : MenuBuilder.Callback {
        override fun onMenuItemSelected(menu: MenuBuilder, item: MenuItem): Boolean {
            return when (item.itemId) {
                menuItemId -> {
                    shortToast(message)
                    true
                }
                secondMenuItemId -> {
                    shortToast(secondMessage)
                    if (textView != null) {
                        copyText(textView)
                    }
                    true
                }
                else -> false
            }
        }

        override fun onMenuModeChange(menu: MenuBuilder) {}
    }))
    optionsMenu.show()
}

fun lockDrawer() {
    APP_ACTIVITY.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
}

fun unlockDrawer() {
    APP_ACTIVITY.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
}

fun hideDrawer() {
    APP_ACTIVITY.toolbar.setNavigationIcon(null)
}

fun hideHome() {
    APP_ACTIVITY.navView.menu.findItem(R.id.home_drawer).setVisible(false)
}

fun showHome() {
    APP_ACTIVITY.navView.menu.findItem(R.id.home_drawer).setVisible(true)
}




















