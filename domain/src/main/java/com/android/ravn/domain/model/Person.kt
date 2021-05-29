package com.android.ravn.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: String?,
    val name: String?,
    val birthYear: String?,
    val skinColor: String?,
    val hairColor: String?,
    val eyeColor: String?,
    val species: String?,
    val homeWorld: String?,
    val vehicles: List<String>?
) : Parcelable