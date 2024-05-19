package com.giraffe.rickmortyapp.presentation.profile

import com.giraffe.rickmortyapp.domain.entities.DetailedCharacterEntity

data class ProfileState(
    val isLoading: Boolean = false,
    val detailedCharacterEntity: DetailedCharacterEntity? = null
)
