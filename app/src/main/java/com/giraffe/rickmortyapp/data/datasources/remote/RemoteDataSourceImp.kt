package com.giraffe.rickmortyapp.data.datasources.remote

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.apollographql.apollo3.ApolloClient
import com.giraffe.CharacterInfoQuery
import com.giraffe.CharactersQuery

class RemoteDataSourceImp(
    private val context: Context,
    private val apolloClient: ApolloClient
) :
    RemoteDataSource {
    override suspend fun getCharacters() = apolloClient
        .query(CharactersQuery())
        .execute()
        .data?.characters?.results ?: emptyList()

    override suspend fun getCharacterInfo(id: String) = apolloClient
        .query(CharacterInfoQuery(id))
        .execute()
        .data?.character

    override suspend fun loadImageToBitmap(imageUrl: String): Bitmap {
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()

        val imageLoader = ImageLoader.Builder(context)
            .build()

        return (imageLoader.execute(request) as SuccessResult).drawable.toBitmap()
    }
}