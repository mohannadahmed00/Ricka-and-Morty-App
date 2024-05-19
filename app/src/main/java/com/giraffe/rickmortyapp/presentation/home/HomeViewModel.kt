package com.giraffe.rickmortyapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giraffe.rickmortyapp.domain.usecases.GetSimpleCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSimpleCharactersUseCase: GetSimpleCharactersUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getSimpleCharacters()
    }

    private fun getSimpleCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(characters = getSimpleCharactersUseCase(), isLoading = false) }
        }
    }

}