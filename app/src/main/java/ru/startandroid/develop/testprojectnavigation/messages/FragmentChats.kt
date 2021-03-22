package ru.startandroid.develop.testprojectnavigation.messages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentChatBinding

class FragmentChats : Fragment(R.layout.fragment_chat){
    private lateinit var binding: FragmentChatBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        binding.edittextFragmentChat
    }
}

//TODO: сделать и привязать recyclerView с двумя адаптарами и отобразить dummy сообщения