package com.giraffe.rickmortyapp.domain.usecases

import com.giraffe.rickmortyapp.domain.repository.Repository
import javax.inject.Inject

class GetSimpleCharactersUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getSimpleCharacters()
}