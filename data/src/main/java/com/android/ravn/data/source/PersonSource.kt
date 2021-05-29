package com.android.ravn.data.source

import PeopleListQuery
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.ravn.data.util.mapper.PersonMapper
import com.android.ravn.domain.model.Person
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.fetcher.ApolloResponseFetchers

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
            ).toBuilder()
                .responseFetcher(ApolloResponseFetchers.CACHE_AND_NETWORK)
                .build()
                .await()
        } catch (exception: ApolloException) {
            return LoadResult.Error(exception)
        }

        val pageInfo: PeopleListQuery.PageInfo?
        val data: List<PeopleListQuery.Person>

        people.data?.allPeople.apply {
            pageInfo = this?.pageInfo
            data = this?.people?.filterNotNull() ?: listOf()
        }

        return LoadResult.Page(
            data = data.map { PersonMapper(it).toDomainModel() },
            prevKey = null,
            nextKey = pageInfo?.endCursor
        )
    }

    override fun getRefreshKey(state: PagingState<String, Person>): String? = null
}
