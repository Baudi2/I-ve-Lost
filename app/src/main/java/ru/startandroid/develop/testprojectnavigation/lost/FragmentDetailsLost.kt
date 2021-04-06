package ru.startandroid.develop.testprojectnavigation.lost

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
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentDetailsBinding
import ru.startandroid.develop.testprojectnavigation.recyclerView.HorizontalAdapter
import ru.startandroid.develop.testprojectnavigation.module.HorizontalLayoutItem

class FragmentDetailsLost : Fragment(R.layout.fragment_details), HorizontalAdapter.HorizontalItemClickListener{

    private lateinit var binding : FragmentDetailsBinding
    private val args: FragmentDetailsLostArgs by navArgs()
    private val dummyData = generateItemList(6)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        val header = args.header
        val description = args.description

        val manager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        val adapter = HorizontalAdapter(dummyData, this)
        binding.detailsRecyclerView.adapter = adapter
        binding.apply {
            detailsRecyclerView.layoutManager = manager
            detailsRecyclerView.setHasFixedSize(true)
            snapHelper.attachToRecyclerView(detailsRecyclerView)
            // pager indicator
            detailsRecyclerView.addItemDecoration(LinePagerIndicatorDecoration())
            detailsRecyclerView.setBackgroundColor(resources.getColor(R.color.slightly_grey))

            headerDetailsLost.text = header
            descriptionDetailsLost.text = description
        }
    }

    override fun onHorizontalItemClickListener(position: Int) {
        //clicked item
        val clickedItem = dummyData[position].image
        val action = FragmentDetailsLostDirections.actionFragmentDetailsToFragmentDetailsSelectedPhoto(clickedItem, position)
        findNavController().navigate(action)
    }

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