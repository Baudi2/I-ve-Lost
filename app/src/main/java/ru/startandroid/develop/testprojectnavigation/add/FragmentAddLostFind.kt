package ru.startandroid.develop.testprojectnavigation.add

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentChooseLostFoundBinding

class FragmentAddLostFind : Fragment(R.layout.fragment_choose_lost_found){
    private lateinit var binding : FragmentChooseLostFoundBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChooseLostFoundBinding.bind(view)
        val sentStringLost = context?.getString(R.string.what_you_lost)
        val sentStringFound = context?.getString(R.string.what_you_found)
        var sentIsLost: Boolean

        binding.apply {
            textLost.setOnClickListener {
                sentIsLost = true
                val action = FragmentAddLostFindDirections.actionFragmentAddLostFind2ToFragmentAdd(
                    sentStringLost!!,
                    sentIsLost
                )
                findNavController().navigate(action)
            }

            textFound.setOnClickListener {
                sentIsLost = false
                val action = FragmentAddLostFindDirections.actionFragmentAddLostFind2ToFragmentAdd(
                    sentStringFound!!,
                    sentIsLost
                )
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up)
        val animDelay230 = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up_delay_230)

        binding.apply {
            textLost.startAnimation(anim)
            textFound.startAnimation(animDelay230)
        }
    }
}















