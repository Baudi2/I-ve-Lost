package ru.startandroid.develop.testprojectnavigation.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.startandroid.develop.testprojectnavigation.R

//? нужно будет тут заменить на нормальный ресайкл который поддерживает два viewHolder
class HorizontalAdapter(private val horizontalItem: List<HorizontalLayoutItem>,
    private val listener: HorizontalItemClickListener)
    : RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_recycler_view_item,
        parent, false)

        return HorizontalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        val currentItem = horizontalItem[position]

        holder.horizontalImageView.setImageResource(currentItem.image)
    }

    override fun getItemCount() = horizontalItem.size

    inner class HorizontalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val horizontalImageView: ImageView = itemView.findViewById(R.id.horizontal_image_recycle)
        //? инициализируем внутри viewHolder наш интерфейс слушатель нажатий.
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onHorizontalItemClickListener(position)
                }
            }
        }
    }

    interface HorizontalItemClickListener {
        fun onHorizontalItemClickListener(position: Int)
    }
}