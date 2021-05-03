package ru.startandroid.develop.testprojectnavigation.module

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YourAddNavigate(
    val topic: String?,
    val yourAdDistinction: Int,
    val size: Int
): Parcelable
