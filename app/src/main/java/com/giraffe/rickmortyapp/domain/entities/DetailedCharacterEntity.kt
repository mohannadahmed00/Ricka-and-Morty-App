package com.giraffe.rickmortyapp.domain.entities

import android.graphics.Bitmap

data class DetailedCharacterEntity(
    val id: String,
    val image: Bitmap,
    val name: String,
    val gender: String,
    val status: String,
    val species: String,
    val type: String,
    val location: String,
    val about:String = ""
)