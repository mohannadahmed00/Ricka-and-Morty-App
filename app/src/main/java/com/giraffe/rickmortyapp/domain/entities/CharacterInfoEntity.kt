package com.giraffe.rickmortyapp.domain.entities

data class CharacterInfoEntity(
    val image: String?,
    val name: String?,
    val gender: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val location: String?,
)