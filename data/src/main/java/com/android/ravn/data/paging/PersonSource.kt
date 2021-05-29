package com.android.ravn.data.paging

import PeopleListQuery
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.ravn.data.util.mapper.PersonMapper
import com.android.ravn.domain.model.Person
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException

class PersonSource(
    private val apolloClient: ApolloClient
) : PagingSource<String, Person>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Person> {
        val people = try {
            apolloClient.query(
                PeopleListQuery(
                    first = Input.fromNullable(params.loadSize),
                    after = Input.optional(params.key)
                )
            ).await()
        } catch (exception: ApolloException) {
            return LoadResult.Error(exception)
        }

        val pageInfo = people.data?.allPeople?.pageInfo
        val data = people.data?.allPeople?.people?.filterNotNull()

        return LoadResult.Page(
            data = data?.map { PersonMapper(it).toDomainModel() } ?: listOf(),
            prevKey = null,
            nextKey = pageInfo?.endCursor
        )
    }

    override fun getRefreshKey(state: PagingState<String, Person>): String? = null
}

