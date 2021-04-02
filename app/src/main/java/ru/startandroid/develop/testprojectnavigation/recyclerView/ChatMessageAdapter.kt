package ru.startandroid.develop.testprojectnavigation.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.startandroid.develop.testprojectnavigation.R

class ChatMessageAdapter(
    private val messageItem: List<MessageItem>,
    private val listener: OnMessageClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val FROM_LEFT_MESSAGE_VIEW_HOLDER = 0
        private const val TO_RIGHT_MESSAGE_VIEW_HOLDER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FROM_LEFT_MESSAGE_VIEW_HOLDER) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_left_from, parent, false)
            FromLeftViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_right_to, parent, false)
            ToRightViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == FROM_LEFT_MESSAGE_VIEW_HOLDER) {
            (holder as FromLeftViewHolder).bind(messageItem[position])
        } else {
            (holder as ToRightViewHolder).bind(messageItem[position])
        }
    }

    override fun getItemCount() = messageItem.size

    override fun getItemViewType(position: Int): Int {
        return if (messageItem[position].isLeft != 0) {
            FROM_LEFT_MESSAGE_VIEW_HOLDER
        } else {
            TO_RIGHT_MESSAGE_VIEW_HOLDER
        }
    }

    inner class FromLeftViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onMessageClick(position)
                }
            }
        }

        fun bind(messageItem: MessageItem) {
            itemView.findViewById<ImageView>(R.id.imageview_receiving_person).setImageResource(messageItem.userImage)
            itemView.findViewById<TextView>(R.id.textview_chat_left_from).text = messageItem.lastMessageText
        }
    }

    inner class ToRightViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onMessageClick(position)
                }
            }
        }

        fun bind(messageItem: MessageItem) {
            itemView.findViewById<ImageView>(R.id.current_user_chat_imageview).setImageResource(messageItem.userImage)
            itemView.findViewById<TextView>(R.id.textview_chat_right_to).text = messageItem.lastMessageText
        }
    }

    interface OnMessageClickListener {
        fun onMessageClick(position: Int)
    }
}