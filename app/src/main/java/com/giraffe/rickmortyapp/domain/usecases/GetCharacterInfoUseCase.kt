package com.giraffe.rickmortyapp.domain.usecases

import com.giraffe.rickmortyapp.domain.repository.Repository
import javax.inject.Inject

class GetCharacterInfoUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: String) = repository.getCharacterInfo(id)
}