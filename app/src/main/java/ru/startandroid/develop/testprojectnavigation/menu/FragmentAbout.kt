package ru.startandroid.develop.testprojectnavigation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentAboutBinding
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentProfileBinding
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentSettingsBinding

class FragmentAbout : Fragment(R.layout.fragment_about){

    private lateinit var binding : FragmentAboutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)
    }
}