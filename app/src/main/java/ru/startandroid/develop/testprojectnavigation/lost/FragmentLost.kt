package ru.startandroid.develop.testprojectnavigation.lost

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentLostBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.GridLayoutAdapter
import ru.startandroid.develop.testprojectnavigation.module.GridLayoutItem

class FragmentLost : Fragment(R.layout.fragment_lost), GridLayoutAdapter.OnItemClickListener {


    //? binding; apply; bottomNavigation; fab clickListener, все это законментировано в FragmentProfile.kt
    private lateinit var binding: FragmentLostBinding
    private val exampleList = generateItemListGridLayout(30)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLostBinding.bind(view)

        val manager = GridLayoutManager(activity, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when(position) {
                0 -> 2
                else -> 1
            }
        }

        val adapter = GridLayoutAdapter(exampleList, this, requireContext())
        //? recyclerView закоментирован в FragmentFound тут тоже самое что и там
        binding.recyclerLostView.adapter = adapter
        binding.apply {
            recyclerLostView.layoutManager = manager
            recyclerLostView.setHasFixedSize(true)

            bottomNavLost.setupWithNavController(findNavController())
            bottomNavLost.itemIconSize = 70

            fabLost.setOnClickListener {
                val action = FragmentLostDirections.actionGlobalFragmentAddLostFind2()
                findNavController().navigate(action)
            }
        }

        // подключаем поиск на тулбар с иконкой поиска
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // инициализируем поисковое меню
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    // пока просто показываем тост и отображаем строку которую пользователь ввел в поиск
                    Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show()

                    // убирает клавиатуру с видимости как только запрос был завершен
                    searchView.clearFocus()
                }
                return true
            }

            // здесь ничего не делаем потому что мы не хотем выполнять запрос пока мы все ещё пишем
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    private fun generateItemListGridLayout(size: Int): ArrayList<GridLayoutItem> {
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
                0 -> "Потерял паспорт"
                1 -> "Потерял деньги"
                2 -> "Потерял кота"
                3 -> "Потерял самолет"
                else -> "Потерял телефон"
            }

            val description = when (i % 5) {
                0 -> "Потерял паспорт в Висаитовском районе около пару дней назад. Никак не могу найти, пожалуйста помогите!"
                1 -> "Потерял деньги в Заводском районе вчера в райное 6 вечера, рядом с магазином Магнит. Сумма небольшая"
                2 -> "Потерял кота в Первомайском районе. Кот породой британец, около 6 месяцев, также на нем был ошейник розового цвета. Откликается на имя Барсик"
                3 -> "Потерял коллекционный игрушечный самолет в Ленинском районе 15 сентября 2019 года. Вознраграждение есть!"
                else -> "Потерял телефон в Октябрьском районе телефон марки XiaoMi модель Mi 10T купил совсем недавно."
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
        val clickedItemHeader = exampleList[position].headerText
        val clickedItemDescription = exampleList[position].descriptionText
        val action = FragmentLostDirections.actionFragmentLostToОписание(clickedItemHeader, clickedItemDescription)
        findNavController().navigate(action)
    }
}