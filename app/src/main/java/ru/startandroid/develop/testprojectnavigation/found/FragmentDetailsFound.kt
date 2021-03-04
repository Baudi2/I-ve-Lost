package ru.startandroid.develop.testprojectnavigation.found

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentDetailsFoundBinding

class FragmentDetailsFound : Fragment(R.layout.fragment_details_found){

    private lateinit var binding : FragmentDetailsFoundBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsFoundBinding.bind(view)

    }
}