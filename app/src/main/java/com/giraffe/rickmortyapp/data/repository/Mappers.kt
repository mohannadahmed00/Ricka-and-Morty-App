package com.giraffe.rickmortyapp.data.repository

import android.graphics.Bitmap
import com.giraffe.CharactersQuery
import com.giraffe.rickmortyapp.data.datasources.local.models.CharacterModel
import com.giraffe.rickmortyapp.domain.entities.DetailedCharacterEntity
import com.giraffe.rickmortyapp.domain.entities.SimpleCharacterEntity


fun CharactersQuery.Result.toCharacterModel(image: Bitmap) = CharacterModel(
    id = id?:"",
    name = name?:"",
    image = image,
    type = type?:"",
    gender = gender?:"",
    status = status?:"",
    location = location?.name?:"",
    species = species?:""
)

fun CharacterModel.toSimpleCharacterEntity() = SimpleCharacterEntity(
    id = id,
    name = name,
    image = image,
)

fun CharacterModel.toDetailedCharacterEntity() = DetailedCharacterEntity(
    id = id,
    name = name,
    image = image,
    type = type,
    gender = gender,
    status = status,
    location = location,
    species = species
)
