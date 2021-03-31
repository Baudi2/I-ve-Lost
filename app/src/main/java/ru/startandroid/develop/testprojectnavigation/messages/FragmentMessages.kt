package ru.startandroid.develop.testprojectnavigation.messages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentMessagesBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.GridLayoutItem
import ru.startandroid.develop.testprojectnavigation.recyclerView.MessageFragmentAdapter
import ru.startandroid.develop.testprojectnavigation.recyclerView.MessageItem

class FragmentMessages : Fragment(R.layout.fragment_messages), MessageFragmentAdapter.OnItemClickListener{
    //? binding; apply; bottomNavigation; fab clickListener, все это законментировано в FragmentProfile.kt
    private lateinit var binding : FragmentMessagesBinding
    private var dummyData = ArrayList<MessageItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = LinearLayoutManager(activity)
        dummyData = generateItemList(5)
        binding = FragmentMessagesBinding.bind(view)

        //TODO: передавать при навигации имя юзера чтобы поставить в тулбар

        binding.recyclerMessagesView.adapter = MessageFragmentAdapter(dummyData, this)
        binding.apply {
            bottomNavMessages.setupWithNavController(findNavController())
            bottomNavMessages.itemIconSize = 70

            fabMessages.setOnClickListener {
                val action = FragmentMessagesDirections.actionGlobalFragmentAddLostFind2()
                findNavController().navigate(action)
            }

            recyclerMessagesView.layoutManager = manager
            recyclerMessagesView.setHasFixedSize(true)
        }
    }

    private fun generateItemList(size: Int): ArrayList<MessageItem> {
        // the we create new empty arrayList<>
        val list = ArrayList<MessageItem>()

        // and it uses the size value in the for loop to fill this list with data
        // Note: this is a custom algorithm that has nothing to do neither with android nor recyclerView
        for (i in 0 until size) {
            // this part is only responsible for alternating between our 5 drawables
            val userImage = when (i % 5) {
                0 -> R.drawable.av_one
                1 -> R.drawable.av_two
                2 -> R.drawable.av_fourer
                3 -> R.drawable.av_four
                else -> R.drawable.av_five
            }

            val userName = when (i % 5) {
                0 -> "Хьасан"
                1 -> "Сулиман"
                2 -> "Зайнап"
                3 -> "Адам"
                else -> "Ильяс"
            }

            val lastMessage = when (i % 5) {
                0 -> "Нашел твой потерянный документ по адресу Висоитовский район дом 146. Очень длинное сообщение"
                1 -> "Брат можешь по больше информации предоставить?"
                2 -> "Какого цвета была найденная кошка?"
                3 -> "Ассаламу Аллейкум. Объявление всё ещё акутальное?"
                else -> "Нашел ваш самолет у себя в гараже."
            }

            // creates new ExampleItem and passes through its constructor the necessary data
            val item = MessageItem(userImage, userName, lastMessage)
            list += item
        }
        // after filling the list with data we eventually return it
        return list
    }

    override fun onItemClick(position: Int) {
        val clickedUserName = dummyData[position].userName
        val clickedUserPhoto = dummyData[position].userImage

        val action = FragmentMessagesDirections.actionFragmentMessagesToFragmentChats(clickedUserName, clickedUserPhoto)
        findNavController().navigate(action)
    }
}