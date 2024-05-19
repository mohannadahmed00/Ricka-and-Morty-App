package com.giraffe.rickmortyapp.data.repository

import com.giraffe.CharacterInfoQuery
import com.giraffe.CharactersQuery
import com.giraffe.rickmortyapp.domain.entities.CharacterEntity
import com.giraffe.rickmortyapp.domain.entities.CharacterInfoEntity

fun CharactersQuery.Result.toEntity() = CharacterEntity(
    id = id,
    name = name,
    image = image
)

fun CharacterInfoQuery.Character.toEntity() = CharacterInfoEntity(
    name = name,
    image = image,
    type = type,
    gender = gender,
    status = status,
    location = location?.name,
    species = species
)