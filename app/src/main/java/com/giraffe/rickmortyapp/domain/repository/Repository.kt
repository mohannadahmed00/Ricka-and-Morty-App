package com.giraffe.rickmortyapp.domain.repository

import com.giraffe.rickmortyapp.domain.entities.DetailedCharacterEntity
import com.giraffe.rickmortyapp.domain.entities.SimpleCharacterEntity

interface Repository {
    suspend fun getSimpleCharacters(): List<SimpleCharacterEntity>
    suspend fun getDetailedCharacter(id: String): DetailedCharacterEntity
}