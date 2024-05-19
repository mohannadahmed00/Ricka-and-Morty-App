package com.giraffe.rickmortyapp.common.di

import com.apollographql.apollo3.ApolloClient
import com.giraffe.rickmortyapp.common.utils.Constants
import com.giraffe.rickmortyapp.data.datasorces.remote.RemoteDataSourceImp
import com.giraffe.rickmortyapp.data.repository.RepositoryImp
import com.giraffe.rickmortyapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Constants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(remoteDataSourceImp: RemoteDataSourceImp): Repository =
        RepositoryImp(remoteDataSourceImp)
}