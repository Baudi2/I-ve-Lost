package ru.startandroid.develop.testprojectnavigation.recyclerView

data class MessageItem(
    var itemId: Long,
    val userImage: Int,
    val userName: String,
    val lastMessageText: String,
    val isLeft: Int
    )