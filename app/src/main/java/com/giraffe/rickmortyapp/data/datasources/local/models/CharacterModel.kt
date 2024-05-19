package com.giraffe.rickmortyapp.data.datasources.local.models

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterModel(
    @PrimaryKey
    val id: String,
    val image: Bitmap,
    val name: String,
    val gender: String,
    val status: String,
    val species: String,
    val type: String,
    val location: String,
)