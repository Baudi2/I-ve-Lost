package ru.startandroid.develop.testprojectnavigation.lost

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentLostBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.ExampleAdapter
import ru.startandroid.develop.testprojectnavigation.recyclerView.ExampleItem

class FragmentLost : Fragment(R.layout.fragment_lost), ExampleAdapter.OnItemClickListener{

    private lateinit var binding : FragmentLostBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLostBinding.bind(view)

        setHasOptionsMenu(true)

        val exampleList = generateItemList(30)

        binding.recyclerLostView.adapter = ExampleAdapter(exampleList, this)
        binding.apply {
            recyclerLostView.layoutManager = LinearLayoutManager(requireContext())
            recyclerLostView.setHasFixedSize(true)
        }
    }

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
                0 -> "Потерял паспорт"
                1 -> "Потерял деньги"
                2 -> "Потерял кота"
                3 -> "Потерял самолет"
                else -> "Потерял телефон"
            }

            val description = when (i % 5) {
                0 -> "Потерял паспорт в Висаитовском районе"
                1 -> "Потерял деньги в Заводском районе"
                2 -> "Потерял кота в Первомайском районе"
                3 -> "Потерял самолет в Ленинском районе"
                else -> "Потерял телефон в Октябрьском районе"
            }

            // creates new ExampleItem and passes through its constructor the necessary data
            val item = ExampleItem(drawable, header, description)
            list += item
        }
        // after filling the list with data we eventually return it
        return list
    }

    override fun onItemClick(position: Int) {
        val action = FragmentLostDirections.actionFragmentLostToОписание()
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.second_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.fragmentProfile -> {
                val action = FragmentLostDirections.actionGlobalFragmentProfile()
                findNavController().navigate(action)
            }
            R.id.fragmentMessages -> {
                val action = FragmentLostDirections.actionGlobalFragmentMessages()
                findNavController().navigate(action)
            }
            R.id.fragmentAdd -> {
                val action = FragmentLostDirections.actionGlobalFragmentAdd()
                findNavController().navigate(action)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}