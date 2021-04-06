package ru.startandroid.develop.testprojectnavigation.add

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.*

class FragmentAddChooseCategory : Fragment(R.layout.fragment_add_choose_category){

    private lateinit var binding : FragmentAddChooseCategoryBinding
    private val args: FragmentAddChooseCategoryArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddChooseCategoryBinding.bind(view)
        val sentBoolean = args.isLost

        binding.apply {
            textViewLostDocument.setOnClickListener {
                val sent = context?.getString(R.string.lost_document_text)
                val action =
                    FragmentAddChooseCategoryDirections.actionFragmentAddToFragmentAddEdit(sent!!, sentBoolean)
                findNavController().navigate(action)
            }

            textViewLostMoney.setOnClickListener {
                val sent = context?.getString(R.string.lost_money_text)
                val action =
                    FragmentAddChooseCategoryDirections.actionFragmentAddToFragmentAddEdit(sent!!, sentBoolean)
                findNavController().navigate(action)
            }

            textViewLostAnimals.setOnClickListener {
                val sent = context?.getString(R.string.lost_animal_text)
                val action =
                    FragmentAddChooseCategoryDirections.actionFragmentAddToFragmentAddEdit(sent!!, sentBoolean)
                findNavController().navigate(action)
            }

            textViewLostPeople.setOnClickListener {
                val sent = context?.getString(R.string.lost_people_text)
                val action =
                    FragmentAddChooseCategoryDirections.actionFragmentAddToFragmentAddEdit(sent!!, sentBoolean)
                findNavController().navigate(action)
            }

            textViewLostJewelry.setOnClickListener {
                val sent = context?.getString(R.string.lost_jewelry_text)
                val action =
                    FragmentAddChooseCategoryDirections.actionFragmentAddToFragmentAddEdit(sent!!, sentBoolean)
                findNavController().navigate(action)
            }

            textViewLostEquipment.setOnClickListener {
                val sent = context?.getString(R.string.lost_personal_belongings_text)
                val action =
                    FragmentAddChooseCategoryDirections.actionFragmentAddToFragmentAddEdit(sent!!, sentBoolean)
                findNavController().navigate(action)
            }

            textViewLostOther.setOnClickListener {
                val sent = context?.getString(R.string.lost_other_text)
                val action =
                    FragmentAddChooseCategoryDirections.actionFragmentAddToFragmentAddEdit(sent!!, sentBoolean)
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up)
        val animDelay230 = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up_delay_230)
        val animDelay260 = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up_delay_260)
        val animDelay290 = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up_delay_290)
        val animDelay320 = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up_delay_320)
        val animDelay350 = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up_delay_350)
        val animDelay380 = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up_delay_380)
        val animDelay410 = AnimationUtils.loadAnimation(requireContext(), R.anim.text_show_up_delay_410)


        binding.apply {
            textViewWhatYouLost.startAnimation(anim)
            textViewLostDocument.startAnimation(animDelay230)
            textViewLostMoney.startAnimation(animDelay260)
            textViewLostAnimals.startAnimation(animDelay290)
            textViewLostPeople.startAnimation(animDelay320)
            textViewLostJewelry.startAnimation(animDelay350)
            textViewLostEquipment.startAnimation(animDelay380)
            textViewLostOther.startAnimation(animDelay410)

            textViewWhatYouLost.text = args.lostFound
        }
    }
}