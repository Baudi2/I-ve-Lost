package ru.startandroid.develop.testprojectnavigation.found

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentFoundBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.GridLayoutAdapter
import ru.startandroid.develop.testprojectnavigation.recyclerView.GridLayoutItem
import ru.startandroid.develop.testprojectnavigation.recyclerView.HorizontalAdapter
import ru.startandroid.develop.testprojectnavigation.recyclerView.HorizontalLayoutItem

class FragmentFound : Fragment(R.layout.fragment_found), GridLayoutAdapter.OnItemClickListener {
    //? binding; apply; bottomNavigation; fab clickListener, все это законментировано в FragmentProfile.kt
    private lateinit var binding: FragmentFoundBinding
    private val exampleItem = generateItemList(30)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoundBinding.bind(view)

        val manager = GridLayoutManager(activity, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when(position) {
                0 -> 2
                else -> 1
            }
        }

        //? задаем recyclerView адаптер, для этого нужно передать список данных для заполнения и контекст
        binding.recyclerFoundView.adapter = GridLayoutAdapter(exampleItem, this, requireContext())
        binding.apply {
            recyclerFoundView.layoutManager = manager
            //? оптимизирует работу recyclerView если у его items размер фиксированный
            recyclerFoundView.setHasFixedSize(true)

            bottomNavFound.setupWithNavController(findNavController())
            bottomNavFound.itemIconSize = 70

            fabFound.setOnClickListener {
                val action = FragmentFoundDirections.actionGlobalFragmentAddLostFind2()
                findNavController().navigate(action)
            }
        }
    }

    private fun generateItemList(size: Int): ArrayList<GridLayoutItem> {
        // the we create new empty arrayList<>
        val list = ArrayList<GridLayoutItem>()

        // and it uses the size value in the for loop to fill this list with data
        // Note: this is a custom algorithm that has nothing to do neither with android nor recyclerView
        for (i in 0 until size) {
            // this part is only responsible for alternating between our 5 drawables
            val drawable = when (i % 5) {
                0 -> R.drawable.icon_perosn
                1 -> R.drawable.ic_airplane
                2 -> R.drawable.ic_money
                3 -> R.drawable.ic_wallet
                else -> R.drawable.ic_anchor
            }

            val header = when (i % 5) {
                0 -> "Нашел паспорт"
                1 -> "Нашел деньги"
                2 -> "Нашел кота"
                3 -> "Нашел самолет"
                else -> "Нашел телефон"
            }

            val description = when (i % 5) {
                0 -> "Найден паспорт гражданина Соединенных Штатов Америки в Висаитовском районе. Если ваша фамилия Brown то отзовитесь!"
                1 -> "Найдена небольшая сумма денег от 1000руб. до 10000руб. в Заводском районе. Деньги лежали рядом с магазином Магнит, отклинитесь!"
                2 -> "Найден кот британец по породе рядом с остановкой в Первомайском районе. На коте был розовый ошейник, привел пока домой, съел всю рыбу в доме."
                3 -> "Найден коллекционный игрушечный самолет 1998 года лимитированного издания в Ленинском районе. Был найдем вчера около 6 вечера."
                else -> "Найден телефон выглядит новым, марка XiaoMi модель телефона Mi 10T в Октябрьском районе, не смог разблокировать и позвонить родственникам владельца."
            }

            val time = when (i % 5) {
                0 -> "Сегодня, 16:38"
                1 -> "Вчера, 10:21"
                2 -> "Завтра, 22:19"
                3 -> "Сегодня, 15:03"
                else -> "Вчера, 04:31"
            }

            val views = when (i % 5) {
                0 -> 900
                1 -> 619
                2 -> 532
                3 -> 238
                else -> 152
            }

            // creates new ExampleItem and passes through its constructor the necessary data
            val item = GridLayoutItem(drawable, header, description, time, views)
            list += item
        }
        // after filling the list with data we eventually return it
        return list
    }

    override fun onItemClick(position: Int) {
        val clickedItemHeader = exampleItem[position].headerText
        val clickedItemDescription = exampleItem[position].descriptionText
        val action = FragmentFoundDirections.actionFragmentFoundToFragmentDetailsFound2(clickedItemHeader, clickedItemDescription)
        findNavController().navigate(action)
    }
}