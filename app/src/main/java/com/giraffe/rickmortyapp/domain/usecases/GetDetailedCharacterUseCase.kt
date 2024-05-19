package com.giraffe.rickmortyapp.domain.usecases

import com.giraffe.rickmortyapp.domain.entities.DetailedCharacterEntity
import com.giraffe.rickmortyapp.domain.repository.Repository
import javax.inject.Inject

class GetDetailedCharacterUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: String): DetailedCharacterEntity {
        val character = repository.getDetailedCharacter(id)
        return character.copy(
            about = generateAbout(
                character.gender,
                character.type,
                character.status,
                character.location,
                character.species,
            )
        )
    }

    private fun generateAbout(
        gender: String,
        type: String,
        status: String,
        location: String,
        species: String,
    ): String {
        val strBuilder = StringBuilder()
        strBuilder.append(species)
        if (gender != "unknown" && gender.isNotBlank()) strBuilder.append(" $gender")
        if (status != "unknown" && status.isNotBlank()) strBuilder.append(", now ${if (gender == "Male") "he" else "she"} is $status")
        if (location != "unknown" && location.isNotBlank()) strBuilder.append(", and ${if (gender == "Male") "he" else if (gender == "Female") "she" else "it"} ${if (status == "Dead") "lived" else "lives"} in $location")
        if (type != "unknown" && type.isNotBlank()) strBuilder.append(", ${if (gender == "Male") "his" else "her"} type is $type")
        return strBuilder.toString()
    }
}