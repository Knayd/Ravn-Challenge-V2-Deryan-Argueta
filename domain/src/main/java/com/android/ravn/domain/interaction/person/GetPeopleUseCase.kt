package com.android.ravn.domain.interaction.person

import androidx.paging.PagingData
import com.android.ravn.domain.model.Person
import kotlinx.coroutines.flow.Flow

interface GetPeopleUseCase {
    operator fun invoke(): Flow<PagingData<Person>>
}