package com.giraffe.rickmortyapp.data.datasorces.remote

import com.apollographql.apollo3.ApolloClient
import com.giraffe.CharacterInfoQuery
import com.giraffe.CharactersQuery
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val apolloClient: ApolloClient) :
    RemoteDataSource {
    override suspend fun getCharacters() = apolloClient
        .query(CharactersQuery())
        .execute()
        .data?.characters?.results ?: emptyList()

    override suspend fun getCharacterInfo(id: String) = apolloClient
        .query(CharacterInfoQuery(id))
        .execute()
        .data?.character
}