package ru.startandroid.develop.testprojectnavigation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentMainBinding

class FragmentMain : Fragment(R.layout.fragment_main){

    private lateinit var binding : FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        setHasOptionsMenu(true)

        binding.foundButton.setOnClickListener {
            val action = FragmentMainDirections.actionFragmentMainToFragmentFound()
            findNavController().navigate(action)
        }

        binding.lostButton.setOnClickListener {
            val action = FragmentMainDirections.actionFragmentMainToFragmentSecond()
            findNavController().navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.fragmentProfile -> {
                val action = FragmentMainDirections.actionGlobalFragmentProfile()
                findNavController().navigate(action)
            }
            R.id.fragmentSettings -> {
                val action = FragmentMainDirections.actionGlobalFragmentSettings()
                findNavController().navigate(action)
            }
            R.id.fragmentAbout -> {
                val action = FragmentMainDirections.actionGlobalFragmentAbout()
                findNavController().navigate(action)
            }
            R.id.fragmentReview -> {
                val action = FragmentMainDirections.actionGlobalFragmentReview()
                findNavController().navigate(action)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}