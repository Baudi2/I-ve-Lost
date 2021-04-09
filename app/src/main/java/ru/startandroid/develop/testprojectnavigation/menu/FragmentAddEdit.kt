package ru.startandroid.develop.testprojectnavigation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentAddEditBinding

class FragmentAddEdit: Fragment(R.layout.fragment_add_edit) {
    private lateinit var binding: FragmentAddEditBinding
    private val args: FragmentAddEditArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddEditBinding.bind(view)
        val isLost = args.isLost

        binding.apply {

        }
    }
}