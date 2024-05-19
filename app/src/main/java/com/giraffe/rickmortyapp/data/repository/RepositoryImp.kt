package com.giraffe.rickmortyapp.data.repository

import com.giraffe.rickmortyapp.data.datasorces.remote.RemoteDataSource
import com.giraffe.rickmortyapp.domain.repository.Repository

class RepositoryImp(private val remoteDataSource: RemoteDataSource) : Repository {
    override suspend fun getCharacters() = remoteDataSource.getCharacters().map {
        it?.toEntity()
    }

    override suspend fun getCharacterInfo(id: String) =
        remoteDataSource.getCharacterInfo(id)?.toEntity()
}