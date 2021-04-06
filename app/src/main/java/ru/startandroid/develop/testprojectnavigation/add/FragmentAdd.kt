package ru.startandroid.develop.testprojectnavigation.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentAddBinding

class FragmentAdd: Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private val args: FragmentAddArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        val isLost = args.isLost

        if (isLost) binding.textViewLostNotLost.text = context?.getString(R.string.lost_post)
        if (!isLost) binding.textViewLostNotLost.text = context?.getString(R.string.found_post)
    }
}