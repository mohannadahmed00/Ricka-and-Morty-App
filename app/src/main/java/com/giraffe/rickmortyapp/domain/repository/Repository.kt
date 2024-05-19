package com.giraffe.rickmortyapp.domain.repository

import com.giraffe.rickmortyapp.domain.entities.CharacterEntity
import com.giraffe.rickmortyapp.domain.entities.CharacterInfoEntity

interface Repository {
    suspend fun getCharacters(): List<CharacterEntity?>
    suspend fun getCharacterInfo(id: String): CharacterInfoEntity?
}