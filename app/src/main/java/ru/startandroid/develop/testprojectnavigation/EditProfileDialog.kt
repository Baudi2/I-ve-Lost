package ru.startandroid.develop.testprojectnavigation

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.startandroid.develop.testprojectnavigation.databinding.EditProfileDialogBinding
import ru.startandroid.develop.testprojectnavigation.utils.shortToast
import java.lang.Exception

class EditProfileDialog: DialogFragment() {
    private lateinit var binding: EditProfileDialogBinding
    private var selectedPhotoUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.edit_profile_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EditProfileDialogBinding.bind(view)

        binding.apply {
            buttonNegativeProfileDialog.setOnClickListener {
                dismiss()
            }
            buttonPositiveProfileDialog.setOnClickListener {
                shortToast("Изменения приняты")
                dismiss()
            }
            buttonChangeProfileDialog.setOnClickListener {
                changePhoto()
            }
        }
    }

    private fun changePhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            selectedPhotoUri = data.data

            try {
                selectedPhotoUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            activity?.contentResolver,
                            selectedPhotoUri
                        )
                        binding.imageProfileDialog.setImageBitmap(bitmap)
                    } else {
                        val source = ImageDecoder.createSource(
                            requireActivity().contentResolver,
                            selectedPhotoUri!!
                        )
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        binding.imageProfileDialog.setImageBitmap(bitmap)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}











