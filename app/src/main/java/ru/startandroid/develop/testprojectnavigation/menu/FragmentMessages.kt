package ru.startandroid.develop.testprojectnavigation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentMessagesBinding

class FragmentMessages : Fragment(R.layout.fragment_messages){
    //? binding; apply; bottomNavigation; fab clickListener, все это законментировано в FragmentProfile.kt
    private lateinit var binding : FragmentMessagesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMessagesBinding.bind(view)

        binding.apply {
            bottomNavMessages.setupWithNavController(findNavController())
            bottomNavMessages.itemIconSize = 80

            fabMessages.setOnClickListener {
                val action = FragmentMessagesDirections.actionGlobalFragmentAdd()
                findNavController().navigate(action)
            }
        }
    }
}