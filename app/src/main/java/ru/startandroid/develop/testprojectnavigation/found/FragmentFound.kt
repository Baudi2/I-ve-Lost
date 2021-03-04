package ru.startandroid.develop.testprojectnavigation.found

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentFoundBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.ExampleAdapter
import ru.startandroid.develop.testprojectnavigation.recyclerView.ExampleItem

class FragmentFound : Fragment(R.layout.fragment_found), ExampleAdapter.OnItemClickListener {
    //? binding; apply; bottomNavigation; fab clickListener, все это законментировано в FragmentProfile.kt
    private lateinit var binding: FragmentFoundBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFoundBinding.bind(view)

        val exampleItem = generateItemList(30)

        //? задаем recyclerView адаптер, для этого нужно передать список данных для заполнения и контекст
        binding.recyclerFoundView.adapter = ExampleAdapter(exampleItem, this)
        binding.apply {
            recyclerFoundView.layoutManager = LinearLayoutManager(requireContext())
            //? оптимизирует работу recyclerView если у его items размер фиксированный
            recyclerFoundView.setHasFixedSize(true)

            bottomNavFound.setupWithNavController(findNavController())
            bottomNavFound.itemIconSize = 80

            fabFound.setOnClickListener {
                val action = FragmentFoundDirections.actionGlobalFragmentAdd()
                findNavController().navigate(action)
            }
        }
    }

    //? метод для создание случайных данных для заполнения ими recyclerView
    private fun generateItemList(size: Int): ArrayList<ExampleItem> {
        // the we create new empty arrayList<>
        val list = ArrayList<ExampleItem>()

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
                0 -> "Нашел паспорт в Висаитовском районе"
                1 -> "Нашел деньги в Заводском районе"
                2 -> "Нашел кота в Первомайском районе"
                3 -> "Нашел самолет в Ленинском районе"
                else -> "Нашел телефон в Октябрьском районе"
            }

            // creates new ExampleItem and passes through its constructor the necessary data
            val item = ExampleItem(drawable, header, description)
            list += item
        }
        // after filling the list with data we eventually return it
        return list
    }

    override fun onItemClick(position: Int) {
        val action = FragmentFoundDirections.actionFragmentFoundToFragmentDetailsFound2()
        findNavController().navigate(action)
    }
}