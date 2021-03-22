package ru.startandroid.develop.testprojectnavigation.lost

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentLostBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.GridLayoutAdapter
import ru.startandroid.develop.testprojectnavigation.recyclerView.GridLayoutItem

class FragmentLost : Fragment(R.layout.fragment_lost), GridLayoutAdapter.OnItemClickListener {
    //? binding; apply; bottomNavigation; fab clickListener, все это законментировано в FragmentProfile.kt
    private lateinit var binding: FragmentLostBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLostBinding.bind(view)

        val exampleList = generateItemList(30)
        val manager = GridLayoutManager(activity, 2)

        //? recyclerView закоментирован в FragmentFound тут тоже самое что и там
        binding.recyclerLostView.adapter = GridLayoutAdapter(exampleList, this)
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
                0 -> "Потерял паспорт"
                1 -> "Потерял деньги"
                2 -> "Потерял кота"
                3 -> "Потерял самолет"
                else -> "Потерял телефон"
            }

            val description = when (i % 5) {
                0 -> "Висаитовский район"
                1 -> "Заводский район"
                2 -> "Первомайский район"
                3 -> "Ленинский район"
                else -> "Октябрьский район"
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
        val action = FragmentLostDirections.actionFragmentLostToОписание()
        findNavController().navigate(action)
    }
}