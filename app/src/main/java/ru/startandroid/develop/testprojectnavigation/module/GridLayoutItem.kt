package ru.startandroid.develop.testprojectnavigation.module

data class GridLayoutItem(
    val imageResource: Int, val headerText: String,
    val descriptionText: String, val timeText: String, val viewsCount: Int,
    val location: String, val northPoint: Double, val eastPoint: Double,
    val adType: String, val adTypeObject: String, val category: String
)