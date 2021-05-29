package com.android.ravn.domain.repository

import com.android.ravn.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPeople(): Flow<List<Person>>
}