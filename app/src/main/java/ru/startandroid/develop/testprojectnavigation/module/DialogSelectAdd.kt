package ru.startandroid.develop.testprojectnavigation.module

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DialogSelectAdd(
    val typeOfAdd: Int,
    val headerText: String,
    val topic: String,
    val isLost: Boolean,
    val textViewDefaultText: String
): Parcelable
