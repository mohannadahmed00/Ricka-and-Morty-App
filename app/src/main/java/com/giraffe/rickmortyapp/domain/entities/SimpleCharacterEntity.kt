package com.giraffe.rickmortyapp.domain.entities

import android.graphics.Bitmap

data class SimpleCharacterEntity(
    val id: String,
    val name: String,
    val image: Bitmap,
)
