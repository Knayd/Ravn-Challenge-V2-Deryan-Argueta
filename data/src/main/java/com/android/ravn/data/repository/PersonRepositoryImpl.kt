package com.android.ravn.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.ravn.data.paging.PersonSource
import com.android.ravn.domain.model.Person
import com.android.ravn.domain.repository.PersonRepository
import com.apollographql.apollo.ApolloClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : PersonRepository {

    fun getPeopleList(): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(
                pageSize = SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PersonSource(apolloClient)
            }
        ).flow
    }

    override fun getPeople() = flow {
        val people = listOf(
            Person(
                id = "1",
                name = "Name 1",
                species = "Species 1",
                homeWorld = "HomeTown 1",
                vehicles = null
            ),
            Person(
                id = "2",
                name = "Name 2",
                species = "Species 2",
                homeWorld = "HomeTown 2",
                vehicles = null
            )
        )
        emit(people)
    }

    companion object {
        private const val SIZE = 5
    }

}