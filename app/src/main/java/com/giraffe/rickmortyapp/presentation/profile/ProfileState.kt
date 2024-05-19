package com.giraffe.rickmortyapp.presentation.profile

import com.giraffe.rickmortyapp.domain.entities.CharacterInfoEntity

data class ProfileState(
    val isLoading: Boolean = false,
    val characterInfoEntity: CharacterInfoEntity? = null
)
