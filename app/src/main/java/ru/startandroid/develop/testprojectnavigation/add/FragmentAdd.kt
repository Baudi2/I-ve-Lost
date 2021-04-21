package ru.startandroid.develop.testprojectnavigation.add

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentAddBinding
import ru.startandroid.develop.testprojectnavigation.module.HorizontalLayoutItem
import ru.startandroid.develop.testprojectnavigation.module.HorizontalUriItem
import ru.startandroid.develop.testprojectnavigation.recyclerView.AddUriHorizontalAdapter
import ru.startandroid.develop.testprojectnavigation.recyclerView.HorizontalAdapter
import ru.startandroid.develop.testprojectnavigation.utils.APP_ACTIVITY
import ru.startandroid.develop.testprojectnavigation.utils.shortToast
import java.lang.Exception
import java.util.jar.Manifest

class FragmentAdd : Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private val args: FragmentAddArgs by navArgs()
    private lateinit var images: ArrayList<HorizontalUriItem>
    private lateinit var image: HorizontalUriItem
    private lateinit var adapter:  AddUriHorizontalAdapter
    private val IMAGE_PICK_CODE = 1000
    //private val PERMISSION_CODE_READ = 1001
  //  private val PERMISSION_CODE_WRITE =1002
    private lateinit var imageView: ImageView





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        val isLost = args.isLost

        images = ArrayList()


        //val adapter = HorizontalAdapter(this, )


        //! кнопка при нажатии которой должны быть добавлены данные в базу данных
        binding.apply {
            buttonAddFragment.setOnClickListener {
                shortToast("Данные добавлены")
            }

            //! карта пока реализована по умолчанию
            indicateThePlace.setOnClickListener {
                val northPoint = 43.317596.toString()
                val eastPoint = 45.693837.toString()
                val location = "Сердце Чечни"
                val action = FragmentAddDirections.actionFragmentAddToFragmentGoogleMaps(
                    location,
                    northPoint,
                    eastPoint
                )
                findNavController().navigate(action)
            }

            addPhotoFloatingButton.setOnClickListener {
                pickPhoto()
            }

        }
    }


    private fun pickPhoto() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Pictures: "), 6)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 6) {
            if (resultCode == Activity.RESULT_OK) {
                if (data!!.data != null) {
                    val count = data.clipData!!.itemCount
                    for (i in 0 until count) {
                        val sd = image
                        sd.image =  data.clipData!!.getItemAt(i).uri
                        images.add(sd)
                    }
                } else {
                    val im = data.data
                    images[0].image = im!!
                    adapter = AddUriHorizontalAdapter(images)
                    binding.addPhotoRecyclerView.adapter = adapter
                    val manager = LinearLayoutManager(APP_ACTIVITY, LinearLayoutManager.HORIZONTAL, false)
                    binding.addPhotoRecyclerView.layoutManager = manager
                }
            }
        }
    }
}




