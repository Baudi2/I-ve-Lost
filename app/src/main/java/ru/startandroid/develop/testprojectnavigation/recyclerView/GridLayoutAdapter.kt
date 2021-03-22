package ru.startandroid.develop.testprojectnavigation.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.startandroid.develop.testprojectnavigation.R

class GridLayoutAdapter(
    private val gridLayoutList: List<GridLayoutItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<GridLayoutAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.example_item,
        parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = gridLayoutList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textHeader.text = currentItem.headerText
        holder.textDescription.text = currentItem.descriptionText
        holder.textViewTime.text = currentItem.timeText
        holder.textViewViewCount.text = currentItem.viewsCount.toString()
    }

    override fun getItemCount() = gridLayoutList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.image_holder)
        val textHeader : TextView = itemView.findViewById(R.id.text_view_header)
        val textDescription: TextView = itemView.findViewById(R.id.text_view_location)
        val textViewTime: TextView = itemView.findViewById(R.id.text_view_time)
        val textViewViewCount: TextView = itemView.findViewById(R.id.text_view_views)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}