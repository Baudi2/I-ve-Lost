package ru.startandroid.develop.testprojectnavigation.messages

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentChatBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.ChatMessageAdapter
import ru.startandroid.develop.testprojectnavigation.recyclerView.MessageItem

class FragmentChats : Fragment(R.layout.fragment_chat), ChatMessageAdapter.OnMessageClickListener{
    private lateinit var binding: FragmentChatBinding
    private var dummyMessages = generateChatMessages(5)
    private val args: FragmentChatsArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        val manager = LinearLayoutManager(requireContext())


        binding.recyclerviewFragmentChat.adapter = ChatMessageAdapter(dummyMessages, this)
        binding.apply {
            recyclerviewFragmentChat.setHasFixedSize(true)
            recyclerviewFragmentChat.layoutManager = manager
        }
    }

    override fun onMessageClick(position: Int) {
        Toast.makeText(requireContext(), dummyMessages[position].lastMessageText, Toast.LENGTH_SHORT).show()
    }

    private fun generateChatMessages(size: Int): ArrayList<MessageItem> {
        // the we create new empty arrayList<>
        val list = ArrayList<MessageItem>()

        // and it uses the size value in the for loop to fill this list with data
        // Note: this is a custom algorithm that has nothing to do neither with android nor recyclerView
        for (i in 0 until size) {
            // this part is only responsible for alternating between our 5 drawables
            val userImage = when (i % 5) {
                0 -> R.drawable.av_fourer
                1 -> R.drawable.current_user
                2 -> R.drawable.av_four
                3 -> R.drawable.current_user
                else -> R.drawable.av_two
            }
// R.drawable.av_one
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

            val isLeft = when (i % 5) {
                0 -> 1
                1 -> 0
                2 -> 1
                3 -> 0
                else -> 1
            }

            // creates new ExampleItem and passes through its constructor the necessary data
            val item = MessageItem(userImage, userName, lastMessage, isLeft)
            list += item
        }
        // after filling the list with data we eventually return it
        return list
    }

}










