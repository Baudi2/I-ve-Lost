package ru.startandroid.develop.testprojectnavigation.add


import android.content.Context
import android.content.pm.PackageManager
import com.esafirm.imagepicker.model.Image

import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.esafirm.imagepicker.features.*
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentAddBinding
import ru.startandroid.develop.testprojectnavigation.module.HorizontalUriItem
import ru.startandroid.develop.testprojectnavigation.recyclerView.AddUriHorizontalAdapter
import ru.startandroid.develop.testprojectnavigation.utils.APP_ACTIVITY
import ru.startandroid.develop.testprojectnavigation.utils.shortToast


class FragmentAdd : Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private val args: FragmentAddArgs by navArgs()
    private lateinit var array: ArrayList<HorizontalUriItem>
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:  AddUriHorizontalAdapter
    private var isRecyclerViewDecoration = false
    //! пришлось вынести из за того что launcher не получал контекст
    //! и с val поменять на var
    private lateinit var launcher: ImagePickerLauncher


    private val images = arrayListOf<Image>()



    //! здесь launcher был прописан потому что при запуске приложения он запускался первым и не получал контекста
    override fun onAttach(context: Context) {
        super.onAttach(context)
        launcher = registerImagePicker {
            images.clear()
            recyclerView.removeAllViews()
            array.clear()
            adapter.notifyDataSetChanged()
            images.addAll(it)
            displayImages(images)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        val isLost = args.isLost

        recyclerView = binding.addPhotoRecyclerView
        array = ArrayList()
        adapter = AddUriHorizontalAdapter()

        val manager = LinearLayoutManager(APP_ACTIVITY, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()
        recyclerView.layoutManager = manager
        snapHelper.attachToRecyclerView(recyclerView)

        binding.addPhotoFloatingButton.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    APP_ACTIVITY,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    APP_ACTIVITY,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    100
                )
                return@setOnClickListener
            }
            start()
        }


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
        }
    }

    private fun start() {
            launcher.launch(createConfig())
    }

    private fun createConfig(): ImagePickerConfig {
        return ImagePickerConfig {
            ImagePickerMode.MULTIPLE
            language = "ru"
            isFolderMode = true
            folderTitle = "Хранилище"
            imageTitle = "Выберите фото"
            doneButtonText = "Завершить"
            showDoneButtonAlways = true
            limit = 6
            isShowCamera = false
            savePath = ImagePickerSavePath(
                Environment.getExternalStorageDirectory().path,
                isRelative = false
            ) // can be a full path
            selectedImages = images
        }

    }

    private fun displayImages(images: List<com.esafirm.imagepicker.model.Image>?) {
        if (images == null) return
        for (i in 0..images.size - 1) {
            val temp = HorizontalUriItem(images[i].uri)
            array.add(temp)
        }
        adapter.inputData(array)
        recyclerView.adapter = adapter
        //if (!isRecyclerViewDecoration) recyclerView.addItemDecoration(LinePagerIndicatorDecoration())
        isRecyclerViewDecoration = true
    }
}




