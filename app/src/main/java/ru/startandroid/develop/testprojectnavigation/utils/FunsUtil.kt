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

//? получаем как аргмент сообщение которое надо показать и выводим с ним тост
fun shortToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

//? тоже самое только разная длительность
fun longToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_LONG).show()
}

//? метод в который мы передаем id строки которую нужно получить и он возвращает эту строку
//? из resources.values.strings
fun stringGet(id: Int) = APP_ACTIVITY.resources.getString(id)

//? копирует текст из textView и сохраняет в буфер, i.e. clipboard
fun copyText(textView: TextView) {
    val manager = APP_ACTIVITY.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", textView.text)
    manager.setPrimaryClip(clipData)
}

//? функция вызова popupMenu, используется в нашёл, потерял и фрагменте сообщений
fun explainArgument() {explainArgumentsPopup()} //!.
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
    //? menuHelper нужен для того чтобы показывать иконку т.к. по дефолту не видны
    val optionsMenu = MenuPopupHelper(APP_ACTIVITY, menuBuilder, view)
    optionsMenu.setForceShowIcon(true)
    //? если textView не равен нулю значит мы в фрагменте сообщений, а там нужен аттрибут гравитации
    if (textView != null) optionsMenu.gravity = Gravity.END


    //? слушатель нажатий на отдельный элемент меню
    menuBuilder.setCallback((object : MenuBuilder.Callback {
        override fun onMenuItemSelected(menu: MenuBuilder, item: MenuItem): Boolean {
            //? определяем что произойдет по нажатию также нужно вернуть true
            return when (item.itemId) {
                menuItemId -> {
                    shortToast(message)
                    true
                }
                secondMenuItemId -> {
                    //? показываем это меню если переданный textView неравен null
                    //? что означает что мы находимся в фрагменте сообщений
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
    //? показываем созданное popupMenu
    optionsMenu.show()
}

//? получаем доступ через активити к drawerLayout и у него устанавливаем блок на
//? вытягиваем справа drawerLayout
fun lockDrawer() {
    APP_ACTIVITY.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
}

//? убираем блок установленный ранее
fun unlockDrawer() {
    APP_ACTIVITY.drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
}

//? убираем через toolbar иконку бургер там где она нам не нужна
fun hideDrawer() {
    APP_ACTIVITY.toolbar.setNavigationIcon(null)
}

//? через активити получаем доступ к самому выдвижному меню, далее получаем доступ к
//? элементам его меню, затем находим нужный элемент этого меню и вызываем его метод
//? setVisibility (поставить видимость) и уставливаем видимость на невидимый
fun hideHome() {
    APP_ACTIVITY.navView.menu.findItem(R.id.home_drawer).setVisible(false)
}

//? возвращаем видимость этого элемента обратно
fun showHome() {
    APP_ACTIVITY.navView.menu.findItem(R.id.home_drawer).setVisible(true)
}




















