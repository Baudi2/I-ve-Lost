package ru.startandroid.develop.testprojectnavigation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentProfileBinding

class FragmentProfile : Fragment(R.layout.fragment_profile){

    private lateinit var binding : FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
    }
}