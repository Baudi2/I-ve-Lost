package ru.startandroid.develop.testprojectnavigation.lost

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith
import ru.startandroid.develop.testprojectnavigation.MainActivity
import ru.startandroid.develop.testprojectnavigation.R


@RunWith(AndroidJUnit4ClassRunner::class)
class FragmentLostTest {

    //? проверяю показывается ли фрагмент потерял при запуске приложения
    @Test
    fun test_areViewsVisible() {
        //? запускаем активити а вместе с ним и запускается наш основной фрагмент
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //? проверяем показывается ли основной (home) фрагмент
        onView(withId(R.id.fragmentLost)).check(matches(isDisplayed()))

        //? проверяем показывается ли recyclerView
        onView(withId(R.id.recycler_lost_view)).check(matches(isDisplayed()))

        //? проверяем показывается ли bottom navigation
        onView(withId(R.id.bottom_nav_lost)).check(matches(isDisplayed()))

        //? проверяем показывается ли toolBar сверху
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    //? проверка роботоспособности recyclerView
    @Test
    fun test_isRecyclerViewClickable() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //? проверяем показывается ли recyclerView
        onView(withId(R.id.recycler_lost_view)).check(matches(isDisplayed()))

        //? проверяем нажатие
        onView(withId(R.id.recycler_lost_view)).perform(click())

        //? проверяем перекинуло ли нас на нужный фрагмент после нажатия
        onView(withId(R.id.fragment_lost_details_parent)).check(matches(isDisplayed()))

        //? нажимаем кнопку назад и проверяем перекинуло ли нас обратно
        pressBack()

        onView(withId(R.id.recycler_lost_view)).check(matches(isDisplayed()))
    }

    //? проверка на отображение fragmentDetailsLost
    @Test
    fun test_isFragmentDetailsVisible() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //? проверяем показывается ли recyclerView
        onView(withId(R.id.recycler_lost_view)).check(matches(isDisplayed()))

        //? проверяем нажатие
        onView(withId(R.id.recycler_lost_view)).perform(click())

        //? проверяем перекинуло ли нас на нужный фрагмент после нажатия
        onView(withId(R.id.fragment_lost_details_parent)).check(matches(isDisplayed()))

        //? проверяем видимость картинки
        onView(withId(R.id.details_recycler_view)).check(matches(isDisplayed()))

        //? проверяем видимость двух textView
        onView(withId(R.id.header_details_lost)).check(matches(isDisplayed()))
        onView(withId(R.id.description_details_lost)).check(matches(isDisplayed()))
    }
}