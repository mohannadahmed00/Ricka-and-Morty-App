package com.giraffe.rickmortyapp.data.repository

import com.giraffe.rickmortyapp.data.datasources.local.LocalDataSource
import com.giraffe.rickmortyapp.data.datasources.remote.RemoteDataSource
import com.giraffe.rickmortyapp.domain.entities.SimpleCharacterEntity
import com.giraffe.rickmortyapp.domain.repository.Repository

class RepositoryImp(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override suspend fun getSimpleCharacters(): List<SimpleCharacterEntity> {
        if (!localDataSource.isCharactersDownloaded()) {
            remoteDataSource.getCharacters().forEach {
                it?.let {
                    val image = remoteDataSource.loadImageToBitmap(it.image ?: "")
                    localDataSource.insertCharacter(it.toCharacterModel(image))
                }
            }
            localDataSource.setCharactersDownloadFlag(true)
        }
        return localDataSource.getCharacters().map { it.toSimpleCharacterEntity() }
    }

    override suspend fun getDetailedCharacter(id: String) =
        localDataSource.getCharacterById(id).toDetailedCharacterEntity()
}