package com.android.ravn.data.di

import android.content.Context
import com.android.ravn.data.BuildConfig
import com.android.ravn.data.util.DataBaseConstants.DATABASE_NAME
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Singleton
    @Provides
    fun provideApolloClient(
        @ApplicationContext context: Context
    ): ApolloClient {
        val factory = SqlNormalizedCacheFactory(context, DATABASE_NAME)

        return ApolloClient.builder()
            .serverUrl(BuildConfig.BASE_URL)
            .normalizedCache(factory)
            .build()
    }
}
