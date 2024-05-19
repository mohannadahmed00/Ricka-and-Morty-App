package com.giraffe.rickmortyapp.data.datasources.local

import com.giraffe.rickmortyapp.data.datasources.local.models.CharacterModel

interface LocalDataSource {
    suspend fun insertCharacter(characterModel: CharacterModel): Long
    suspend fun getCharacterById(id: String): CharacterModel
    suspend fun isCharactersDownloaded(): Boolean
    suspend fun setCharactersDownloadFlag(flag: Boolean)
    suspend fun getCharacters(): List<CharacterModel>
}