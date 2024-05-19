package com.giraffe.rickmortyapp.presentation.home

import com.giraffe.rickmortyapp.domain.entities.SimpleCharacterEntity

data class HomeState(
    val isLoading: Boolean = true,
    val characters: List<SimpleCharacterEntity> = emptyList()
)
