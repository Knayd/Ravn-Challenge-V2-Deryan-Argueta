package com.android.ravn.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.ravn.data.source.PersonSource
import com.android.ravn.domain.model.Person
import com.android.ravn.domain.repository.PersonRepository
import com.apollographql.apollo.ApolloClient
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : PersonRepository {

    override fun getPeople(): Flow<PagingData<Person>> {
        return Pager(
            config = PagingConfig(
                pageSize = SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PersonSource(apolloClient) }
        ).flow
    }

    companion object {
        private const val SIZE = 5
    }
}
