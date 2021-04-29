package ru.startandroid.develop.testprojectnavigation.found

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import ru.startandroid.develop.testprojectnavigation.recyclerView.LinePagerIndicatorDecoration
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentDetailsFoundBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.HorizontalAdapter
import ru.startandroid.develop.testprojectnavigation.module.HorizontalLayoutItem
import ru.startandroid.develop.testprojectnavigation.utils.lockDrawer

class FragmentDetailsFound : Fragment(R.layout.fragment_details_found), HorizontalAdapter.HorizontalItemClickListener{

    private lateinit var binding : FragmentDetailsFoundBinding
    private val args: FragmentDetailsFoundArgs by navArgs()
    private val dummyData = generateItemList(6)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsFoundBinding.bind(view)

        val header = args.clickedItem.header
        val description = args.clickedItem.description

        val manager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()

        binding.detailtFoundRecyclerView.adapter = HorizontalAdapter(dummyData, this)
        binding.apply {
            detailtFoundRecyclerView.setHasFixedSize(true)
            detailtFoundRecyclerView.layoutManager = manager
            //? при перемещении элементы четко встают в свою позицию т.е. не гуляют свободно
            snapHelper.attachToRecyclerView(detailtFoundRecyclerView)
            //? связываем наш индикатор элементов с recyclerView
            detailtFoundRecyclerView.addItemDecoration(LinePagerIndicatorDecoration())

            headerFoundDetails.text = header
            descriptionFoundDetails.text = description

            //? при нажатии показать на карте передаем в фрагмент с картой имя места и его координаты
            detailsFoundShowMap.setOnClickListener {
                val action = FragmentDetailsFoundDirections.actionFragmentDetailsFoundToFragmentGoogleMaps(
                    args.clickedItem.location, args.clickedItem.northPoint.toString(), args.clickedItem.eastPoint.toString()
                )
                findNavController().navigate(action)
            }

            //? меняем тип описания в зависимости от того на какое объявление мы перешли
            //? если объявление о документах то будет описание про то что потерян докумен и его тип и т.д.
            chooseCategoryFoundFragment.text = args.clickedItem.category
            adTypeFoundTextView.text = args.clickedItem.adType
            adTypeObjectFoundTextView.text = args.clickedItem.adTypeObject
        }
    }

//? блокируем появление drawerLayout при заходе в этот фрагмент
    override fun onStart() {
        super.onStart()
        lockDrawer()
    }

    //? слушатель нажатий на нашем recyclerView, при нажатии переходим на фрагмент где можно зумить фото
    //? также передаем позицию нажетого фото чтобы там recyclerView открылся на нужном месте.
    override fun onHorizontalItemClickListener(position: Int) {
        val clickedItem = dummyData[position].image
        val action = FragmentDetailsFoundDirections.actionFragmentDetailsFoundToFragmentDetailsSelectedPhoto(clickedItem, position)
        findNavController().navigate(action)
    }

    //? метод для генерации временных данных
    private fun generateItemList(size: Int): ArrayList<HorizontalLayoutItem> {
        // the we create new empty arrayList<>
        val list = ArrayList<HorizontalLayoutItem>()

        // and it uses the size value in the for loop to fill this list with data
        // Note: this is a custom algorithm that has nothing to do neither with android nor recyclerView
        for (i in 0 until size) {
            // this part is only responsible for alternating between our 5 drawables
            val drawable = when (i % 6) {
                0 -> R.drawable.stone
                1 -> R.drawable.charger
                2 -> R.drawable.headphones
                3 -> R.drawable.outside
                4 -> R.drawable.pen
                5 -> R.drawable.ring
                else -> R.drawable.ring
            }

            // creates new ExampleItem and passes through its constructor the necessary data
            val item = HorizontalLayoutItem(drawable, 0)
            list += item
        }
        // after filling the list with data we eventually return it
        return list
    }
}