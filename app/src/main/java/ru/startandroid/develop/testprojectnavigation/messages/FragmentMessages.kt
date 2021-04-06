package ru.startandroid.develop.testprojectnavigation.messages

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.view.menu.MenuPopupHelper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentMessagesBinding
import ru.startandroid.develop.testprojectnavigation.utils.hideKeyboard
import ru.startandroid.develop.testprojectnavigation.recyclerView.ChatMessageAdapter
import ru.startandroid.develop.testprojectnavigation.module.MessageItem

class FragmentMessages : Fragment(R.layout.fragment_messages), ChatMessageAdapter.OnMessageClickListener{
    private lateinit var binding: FragmentMessagesBinding
    private var dummyMessages = generateChatMessages(7)
    private val args: FragmentMessagesArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMessagesBinding.bind(view)

        val manager = LinearLayoutManager(requireContext())
        val adapter = ChatMessageAdapter(dummyMessages, this)

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (itemCount != -1 || itemCount != 0) {
                    binding.recyclerviewFragmentChat.scrollToPosition(positionStart - itemCount + 1)
                }
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                binding.recyclerviewFragmentChat.scrollToPosition(itemCount)
            }
        })

        binding.recyclerviewFragmentChat.adapter = adapter
        binding.apply {
            recyclerviewFragmentChat.setHasFixedSize(true)
            recyclerviewFragmentChat.layoutManager = manager
        }

        binding.recyclerviewFragmentChat.addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
            if (bottom < oldBottom) {
                binding.recyclerviewFragmentChat.postDelayed({
                    if (binding.recyclerviewFragmentChat.adapter!!.itemCount != -1) {
                        binding.recyclerviewFragmentChat.scrollToPosition(
                            binding.recyclerviewFragmentChat.adapter!!.itemCount - 1
                        )
                    }
                }, 0)
            }
        }
    }

    override fun onMessageClick(position: Int, itemView: View) {
        showPopup(itemView)
    }

    @SuppressLint("RestrictedApi")
    private fun showPopup(view: View) {
        val menuBuilder = MenuBuilder(requireContext())
        val menuInflater = MenuInflater(requireContext())
        menuInflater.inflate(R.menu.chat_fragment_popup_menu, menuBuilder)
        val optionsMenu = MenuPopupHelper(requireContext(), menuBuilder, view)
        optionsMenu.setForceShowIcon(true)
        optionsMenu.gravity = Gravity.END


        menuBuilder.setCallback((object:MenuBuilder.Callback{
            override fun onMenuItemSelected(menu: MenuBuilder, item: MenuItem): Boolean {
                return when(item.itemId) {
                    R.id.chat_fragment_popup_delete -> {
                        Toast.makeText(requireContext(), "Сообщение удалено", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

            override fun onMenuModeChange(menu: MenuBuilder) {}
        }))
        optionsMenu.show()
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard(requireView(), requireActivity())
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










