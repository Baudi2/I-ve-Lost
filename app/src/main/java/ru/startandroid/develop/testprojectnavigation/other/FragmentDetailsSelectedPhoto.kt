package ru.startandroid.develop.testprojectnavigation.other

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.DetailsSelectedPhotoBinding

class FragmentDetailsSelectedPhoto: Fragment(R.layout.details_selected_photo) {
    private lateinit var binding: DetailsSelectedPhotoBinding
    private val args: FragmentDetailsSelectedPhotoArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailsSelectedPhotoBinding.bind(view)
        val receivedImage = args.selectedPhoto

        binding.apply{
            detailsSelectedImageView.setImageResource(receivedImage)
            respondToPhotoTextView.setOnClickListener {
                Toast.makeText(requireContext(), "Перенаправляем на фрагмент с сообещниями", Toast.LENGTH_SHORT).show()
            }
        }
    }
}