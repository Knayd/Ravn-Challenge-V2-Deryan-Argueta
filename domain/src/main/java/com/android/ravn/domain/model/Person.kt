package com.android.ravn.domain.model

data class Person(
    val id: String?,
    val name: String?,
    val species: String?,
    val homeTown: String?,
    val vehicles: List<String>?
)