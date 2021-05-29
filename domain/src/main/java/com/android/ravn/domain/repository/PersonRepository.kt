package com.android.ravn.domain.repository

import androidx.paging.PagingData
import com.android.ravn.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPeople(): Flow<PagingData<Person>>
}