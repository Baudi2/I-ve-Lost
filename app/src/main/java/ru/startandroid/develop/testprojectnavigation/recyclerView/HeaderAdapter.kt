package ru.startandroid.develop.testprojectnavigation.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.module.HeaderItem

//? типичный класс адаптер, единственная разница от обычного адаптера то что в качестве viewHolder
//? мы используем не одноименный класс а RecyclerView.ViewHolder, это позволяет нам использовать в
//? этом адаптаре более одного viewHolder
class HeaderAdapter(private val context: Context, private val listener: HeaderItemListener,
private val headerItemList: List<HeaderItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.header_item_lost_found, parent, false)
        return HeaderItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeaderItemViewHolder).bind(headerItemList[position])
    }

    override fun getItemCount() = 8

    inner class HeaderItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(headerItem: HeaderItem) {
            itemView.findViewById<TextView>(R.id.lost_found_item_text_view).text = headerItem.headerTopic
            itemView.findViewById<ImageView>(R.id.lost_found_item_image_view).setImageResource(headerItem.headerImage)
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onHeaderItemListener(position)
                }
            }
        }
    }

    interface HeaderItemListener {
        fun onHeaderItemListener(position: Int)
    }
}