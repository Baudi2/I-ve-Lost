package ru.startandroid.develop.testprojectnavigation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentSettingsBinding
import ru.startandroid.develop.testprojectnavigation.utils.lockDrawer
import ru.startandroid.develop.testprojectnavigation.utils.showHome

class FragmentSettings : Fragment(R.layout.fragment_settings){

    private lateinit var binding : FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
    }

    override fun onStart() {
        super.onStart()
        showHome()
    }
}