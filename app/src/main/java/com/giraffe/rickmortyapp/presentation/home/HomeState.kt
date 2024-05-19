package com.giraffe.rickmortyapp.presentation.home

import com.giraffe.rickmortyapp.domain.entities.CharacterEntity

data class HomeState(
    val isLoading: Boolean = false,
    val characters: List<CharacterEntity> = emptyList()
)
