package com.giraffe.rickmortyapp.data.datasorces.remote

import com.giraffe.CharacterInfoQuery
import com.giraffe.CharactersQuery

interface RemoteDataSource {
    suspend fun getCharacters(): List<CharactersQuery.Result?>
    suspend fun getCharacterInfo(id: String): CharacterInfoQuery.Character?
}